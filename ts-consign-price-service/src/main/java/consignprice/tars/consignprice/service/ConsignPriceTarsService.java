package consignprice.tars.consignprice.service;

import consignprice.tars.consignprice.ConsignPriceTars;
import consignprice.tars.consignprice.ResponseConsignPrice;
import consignprice.tars.consignprice.ResponseDouble;
import consignprice.tars.consignprice.ResponseString;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 11:54
 * @Description:
 */
public interface ConsignPriceTarsService {
    ResponseDouble getPriceByWeightAndRegion(double weight, boolean isWithinRegion);

    ResponseString queryPriceInformation();

    ResponseConsignPrice createAndModifyPrice(ConsignPriceTars config);

    ResponseConsignPrice getPriceConfig();
}
