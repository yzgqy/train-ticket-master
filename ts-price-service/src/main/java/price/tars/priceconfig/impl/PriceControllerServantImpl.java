package price.tars.priceconfig.impl;

import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import price.tars.priceconfig.PriceConfigTars;
import price.tars.priceconfig.PriceControllerServant;
import price.tars.priceconfig.ResponsePriceConfig;
import price.tars.priceconfig.ResponsePriceConfigs;
import price.tars.priceconfig.service.PriceTarsService;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 18:16
 * @Description:
 */
@TarsServant("PriceObj")
public class PriceControllerServantImpl implements PriceControllerServant {

    @Autowired
    PriceTarsService priceTarsService;

    @Override
    public String home() {
        return "Welcome to [ Price Service ] !";
    }

    @Override
    public ResponsePriceConfig query(String routeId, String trainType) {
        return priceTarsService.findByRouteIdAndTrainType(routeId, trainType);
    }

    @Override
    public ResponsePriceConfigs queryAll() {
        return priceTarsService.findAllPriceConfig();
    }

    @Override
    public ResponsePriceConfig create(PriceConfigTars info) {
        return priceTarsService.createNewPriceConfig(info);
    }

    @Override
    public ResponsePriceConfig delete(PriceConfigTars info) {
        return priceTarsService.deletePriceConfig(info);
    }

    @Override
    public ResponsePriceConfig update(PriceConfigTars info) {
        return priceTarsService.updatePriceConfig(info);
    }
}
