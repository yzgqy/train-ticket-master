package consignprice.tars.consignprice.impl;

import com.qq.tars.spring.annotation.TarsServant;
import consignprice.tars.consignprice.*;
import consignprice.tars.consignprice.service.ConsignPriceTarsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 11:53
 * @Description:
 */
@TarsServant("ConsignPriceObj")
public class ConsignPriceControllerServantImpl implements ConsignPriceControllerServant {
    @Autowired
    private ConsignPriceTarsService consignPriceTarsService;

    @Override
    public String home() {
        return "Welcome to [ ConsignPrice Service ] !";
    }

    @Override
    public ResponseDouble getPriceByWeightAndRegion(String weight, String isWithinRegion) {
        return consignPriceTarsService.getPriceByWeightAndRegion(Double.parseDouble(weight),
                Boolean.parseBoolean(isWithinRegion));
    }

    @Override
    public ResponseString getPriceInfo() {
        return consignPriceTarsService.queryPriceInformation();
    }

    @Override
    public ResponseConsignPrice getPriceConfig() {
        return consignPriceTarsService.getPriceConfig();
    }

    @Override
    public ResponseConsignPrice modifyPriceConfig(ConsignPriceTars priceConfig) {
        return consignPriceTarsService.createAndModifyPrice(priceConfig);
    }
}
