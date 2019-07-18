package consignprice.tars.consignprice.service;

import consignprice.entity.ConsignPrice;
import consignprice.repository.ConsignPriceConfigRepository;
import consignprice.tars.consignprice.ConsignPriceTars;
import consignprice.tars.consignprice.ResponseConsignPrice;
import consignprice.tars.consignprice.ResponseDouble;
import consignprice.tars.consignprice.ResponseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 11:55
 * @Description:
 */
@Service
public class ConsignPriceTarsServiceImpl implements ConsignPriceTarsService{
    @Autowired
    private ConsignPriceConfigRepository repository;

    @Override
    public ResponseDouble getPriceByWeightAndRegion(double weight, boolean isWithinRegion) {
        ConsignPrice priceConfig = repository.findByIndex(0);
        double price = 0;
        double initialPrice = priceConfig.getInitialPrice();
        if (weight <= priceConfig.getInitialWeight()) {
            price = initialPrice;
        } else {
            double extraWeight = weight - priceConfig.getInitialWeight();
            if (isWithinRegion)
                price = initialPrice + extraWeight * priceConfig.getWithinPrice();
            else
                price = initialPrice + extraWeight * priceConfig.getBeyondPrice();
        }
        return new ResponseDouble(1, "Success", price);
    }

    @Override
    public ResponseString queryPriceInformation() {
        StringBuilder sb = new StringBuilder();
        ConsignPrice price = repository.findByIndex(0);
        sb.append("The price of weight within ");
        sb.append(price.getInitialWeight());
        sb.append(" is ");
        sb.append(price.getInitialPrice());
        sb.append(". The price of extra weight within the region is ");
        sb.append(price.getWithinPrice());
        sb.append(" and beyond the region is ");
        sb.append(price.getBeyondPrice());
        sb.append("\n");
        return new ResponseString(1, "Success", sb.toString());

    }

    @Override
    public ResponseConsignPrice createAndModifyPrice(ConsignPriceTars config) {
        System.out.println("[Consign Price Service][Create New Price Config]");
        //更新price
        ConsignPrice originalConfig;
        if (repository.findByIndex(0) != null)
            originalConfig = repository.findByIndex(0);
        else
            originalConfig = new ConsignPrice();
        originalConfig.setId(UUID.fromString(config.getId()));
        originalConfig.setIndex(0);
        originalConfig.setInitialPrice(config.getInitialPrice());
        originalConfig.setInitialWeight(config.getInitialWeight());
        originalConfig.setWithinPrice(config.getWithinPrice());
        originalConfig.setBeyondPrice(config.getBeyondPrice());
        repository.save(originalConfig);
        return new ResponseConsignPrice(1, "Success", originalConfig.toTars());

    }

    @Override
    public ResponseConsignPrice getPriceConfig() {
        return new ResponseConsignPrice(1, "Success", repository.findByIndex(0).toTars());
    }
}
