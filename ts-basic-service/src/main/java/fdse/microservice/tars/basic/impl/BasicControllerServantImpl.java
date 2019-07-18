package fdse.microservice.tars.basic.impl;

import com.qq.tars.spring.annotation.TarsServant;
import fdse.microservice.service.BasicService;
import fdse.microservice.tars.basic.BasicControllerServant;
import fdse.microservice.tars.basic.ResponseString;
import fdse.microservice.tars.basic.ResponseTravelResult;
import fdse.microservice.tars.basic.TravelTars;
import fdse.microservice.tars.basic.service.BasicTarsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 10:36
 * @Description:
 */
@TarsServant("BasicObj")
public class BasicControllerServantImpl implements BasicControllerServant {
    @Autowired
    private BasicTarsService basicTarsService;

    @Override
    public String home() {
        return "Welcome to [ Basic Service ] !";
    }

    @Override
    public ResponseTravelResult queryForTravel(TravelTars info) {
        return basicTarsService.queryForTravel(info);
    }

    @Override
    public ResponseString queryForStationId(String stationName) {
        return basicTarsService.queryForStationId(stationName);
    }
}
