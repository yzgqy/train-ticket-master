package price.tars.priceconfig.service;

import price.entity.PriceConfig;
import price.tars.priceconfig.PriceConfigTars;
import price.tars.priceconfig.ResponsePriceConfig;
import price.tars.priceconfig.ResponsePriceConfigs;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 10:08
 * @Description:
 */
public interface PriceTarsService {
    ResponsePriceConfig createNewPriceConfig(PriceConfigTars priceConfig);

    PriceConfig findById(String id);

    ResponsePriceConfig findByRouteIdAndTrainType(String routeId, String trainType);

    ResponsePriceConfigs findAllPriceConfig();

    ResponsePriceConfig deletePriceConfig(PriceConfigTars c);

    ResponsePriceConfig updatePriceConfig(PriceConfigTars c);
}
