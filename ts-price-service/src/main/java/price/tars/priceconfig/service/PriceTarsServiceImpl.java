package price.tars.priceconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import price.entity.PriceConfig;
import price.repository.PriceConfigRepository;
import price.tars.priceconfig.PriceConfigTars;
import price.tars.priceconfig.ResponsePriceConfig;
import price.tars.priceconfig.ResponsePriceConfigs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 10:09
 * @Description:
 */
@Service
public class PriceTarsServiceImpl implements PriceTarsService{

    @Autowired
    private PriceConfigRepository priceConfigRepository;

    @Override
    public ResponsePriceConfig createNewPriceConfig(PriceConfigTars createAndModifyPriceConfig) {
        System.out.println("[Price Service][Create New Price Config]");
        PriceConfig priceConfig = null;
        // create
        if (createAndModifyPriceConfig.getId() == null || createAndModifyPriceConfig.getId().toString().length() < 10) {
            priceConfig = new PriceConfig();
            priceConfig.setId(UUID.randomUUID());
            priceConfig.setBasicPriceRate(createAndModifyPriceConfig.getBasicPriceRate());
            priceConfig.setFirstClassPriceRate(createAndModifyPriceConfig.getFirstClassPriceRate());
            priceConfig.setRouteId(createAndModifyPriceConfig.getRouteId());
            priceConfig.setTrainType(createAndModifyPriceConfig.getTrainType());
            priceConfigRepository.save(priceConfig);
        } else {
            // modify
            priceConfig = priceConfigRepository.findById(UUID.fromString(createAndModifyPriceConfig.getId()));
            if (priceConfig == null) {
                priceConfig = new PriceConfig();
                priceConfig.setId(UUID.fromString(createAndModifyPriceConfig.getId()));
            }
            priceConfig.setBasicPriceRate(createAndModifyPriceConfig.getBasicPriceRate());
            priceConfig.setFirstClassPriceRate(createAndModifyPriceConfig.getFirstClassPriceRate());
            priceConfig.setRouteId(createAndModifyPriceConfig.getRouteId());
            priceConfig.setTrainType(createAndModifyPriceConfig.getTrainType());
            priceConfigRepository.save(priceConfig);
        }
        return new ResponsePriceConfig(1, "Create success", priceConfig.toTars());

    }

    @Override
    public PriceConfig findById(String id) {
        System.out.println("[Price Service][Find By Id] ID:" + id);
        PriceConfig priceConfig = priceConfigRepository.findById(UUID.fromString(id));
        return priceConfig;
    }

    @Override
    public ResponsePriceConfig findByRouteIdAndTrainType(String routeId, String trainType) {
        System.out.println("[Price Service][Find By Route And Train Type] Rote:" + routeId + "Train Type:" + trainType);
        PriceConfig priceConfig = priceConfigRepository.findByRouteIdAndTrainType(routeId, trainType);
        System.out.println("[Price Service][Find By Route Id And Train Type]");

        if (priceConfig == null) {
            return new ResponsePriceConfig(0, "No that config", null);
        } else {
            return new ResponsePriceConfig(1, "Success", priceConfig.toTars());
        }
    }

    @Override
    public ResponsePriceConfigs findAllPriceConfig() {
        List<PriceConfig> list = priceConfigRepository.findAll();
        List<PriceConfigTars> priceConfigTarsList = new ArrayList<>();
        for(PriceConfig priceConfig:list){
            priceConfigTarsList.add(priceConfig.toTars());
        }
        if (list == null) {
            list = new ArrayList<>();
        }

        if (list.size() > 0) {
            return new ResponsePriceConfigs(1, "Success", priceConfigTarsList);
        } else {
            return new ResponsePriceConfigs(0, "No price config", null);
        }
    }

    @Override
    public ResponsePriceConfig deletePriceConfig(PriceConfigTars c) {
        PriceConfig priceConfig = priceConfigRepository.findById(UUID.fromString(c.getId()));
        if (priceConfig == null) {
            return new ResponsePriceConfig(0, "No that config", null);
        } else {
            PriceConfig pc = new PriceConfig();
            pc.setId(UUID.fromString(c.getId()));
            pc.setRouteId(c.getRouteId());
            pc.setTrainType(c.getTrainType());
            pc.setBasicPriceRate(c.getBasicPriceRate());
            pc.setFirstClassPriceRate(c.getFirstClassPriceRate());
            priceConfigRepository.delete(pc);
            return new ResponsePriceConfig(1, "Delete success", pc.toTars());
        }
    }

    @Override
    public ResponsePriceConfig updatePriceConfig(PriceConfigTars c) {
        PriceConfig priceConfig = priceConfigRepository.findById(UUID.fromString(c.getId()));
        if (priceConfig == null) {
            return new ResponsePriceConfig(0, "No that config", null);
        } else {
            priceConfig.setId(UUID.fromString(c.getId()));
            priceConfig.setBasicPriceRate(c.getBasicPriceRate());
            priceConfig.setFirstClassPriceRate(c.getFirstClassPriceRate());
            priceConfig.setRouteId(c.getRouteId());
            priceConfig.setTrainType(c.getTrainType());
            priceConfigRepository.save(priceConfig);
            return new ResponsePriceConfig(1, "Update success", priceConfig.toTars());
        }
    }
}
