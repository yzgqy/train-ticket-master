package fdse.microservice.tars.basic.service;

import fdse.microservice.tars.basic.ResponseString;
import fdse.microservice.tars.basic.ResponseTravelResult;
import fdse.microservice.tars.basic.TravelTars;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 10:37
 * @Description:
 */
public interface BasicTarsService {
    ResponseTravelResult queryForTravel(TravelTars info);
    ResponseString queryForStationId(String stationName);
}
