package fdse.microservice.tars.station.service;

import fdse.microservice.tars.station.*;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 15:31
 * @Description:
 */
public interface StationTarsService {
    //CRUD
    ResponseStation create(StationTars info);

    boolean exist(String stationName);

    ResponseStation update(StationTars info);

    ResponseStation delete(StationTars info);

    ResponseStations query();

    ResponseString queryForId(String stationName);

    ResponseStringList queryForIdBatch(List<String> nameList);

    ResponseString queryById(String stationId);

    ResponseStringList queryByIdBatch(List<String> stationIdList);

}
