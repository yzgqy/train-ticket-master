package fdse.microservice.tars.station.impl;

import com.qq.tars.spring.annotation.TarsServant;
import fdse.microservice.tars.station.*;
import fdse.microservice.tars.station.service.StationTarsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 15:31
 * @Description:
 */
@TarsServant("StationObj")
public class StationControllerServantImpl implements StationControllerServant {
    @Autowired
    private StationTarsService stationTarsService;

    @Override
    public String home() {
        return "Welcome to [ Station Service ] !";
    }

    @Override
    public ResponseStations query() {
        return stationTarsService.query();
    }

    @Override
    public ResponseStation create(StationTars station) {
        return stationTarsService.create(station);
    }

    @Override
    public ResponseStation update(StationTars station) {
        return stationTarsService.update(station);
    }

    @Override
    public ResponseStation delete(StationTars station) {
        return stationTarsService.delete(station);
    }

    @Override
    public ResponseString queryForStationId(String stationName) {
        return stationTarsService.queryForId(stationName);
    }

    @Override
    public ResponseStringList queryForIdBatch(List<String> stationNameList) {
        return stationTarsService.queryForIdBatch(stationNameList);
    }

    @Override
    public ResponseString queryById(String stationId) {
        System.out.println("[Station Service] Query By Id:" + stationId);
        // string
        return stationTarsService.queryById(stationId);
    }

    @Override
    public ResponseStringList queryForNameBatch(List<String> stationIdList) {
        return stationTarsService.queryByIdBatch(stationIdList);

    }
}
