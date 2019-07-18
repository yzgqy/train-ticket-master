package fdse.microservice.tars.station.service;

import fdse.microservice.entity.Station;
import fdse.microservice.repository.StationRepository;
import fdse.microservice.tars.station.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 15:31
 * @Description:
 */
@Service
public class StationTarsServiceImpl implements StationTarsService {

    @Autowired
    private StationRepository repository;

    @Override
    public ResponseStation create(StationTars station) {
        if (repository.findById(station.getId()) == null) {
            station.setStayTime(station.getStayTime());
            repository.save(new Station(station));
            return new ResponseStation(1, "Create success", station);
        }
        return new ResponseStation(0, "Already exists", station);

    }

    @Override
    public boolean exist(String stationName) {
        boolean result = false;
        if (repository.findByName(stationName) != null) {
            result = true;
        }
        return result;
    }

    @Override
    public ResponseStation update(StationTars info) {
        if (repository.findById(info.getId()) == null) {
            return new ResponseStation(0, "Station not exist", info);
        } else {
            Station station = new Station(info.getId(), info.getName());
            station.setStayTime(info.getStayTime());
            repository.save(station);
            return new ResponseStation(1, "Update success", station.toTars());
        }
    }

    @Override
    public ResponseStation delete(StationTars info) {
        if (repository.findById(info.getId()) != null) {
            Station station = new Station(info.getId(), info.getName());
            repository.delete(station);
            return new ResponseStation(1, "Delete success", station.toTars());
        }
        return new ResponseStation(0, "Station not exist", info);

    }

    @Override
    public ResponseStations query() {
        List<Station> stations = repository.findAll();
        List<StationTars> stationTars = new ArrayList<>();
        if (stations != null && stations.size() > 0) {
            for(Station station:stations){
                stationTars.add(station.toTars());
            }
            return new ResponseStations(1, "Find all content", stationTars);
        } else {
            return new ResponseStations(0, "No content", null);
        }
    }

    @Override
    public ResponseString queryForId(String stationName) {
        Station station = repository.findByName(stationName);

        if (station  != null) {
            return new ResponseString(1, "Success", station.getId());
        } else {
            return new ResponseString(0, "Not exists", stationName);
        }
    }

    @Override
    public ResponseStringList queryForIdBatch(List<String> nameList) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            Station station = repository.findByName(nameList.get(i));
            if (station == null) {
                result.add("Not Exist");
            } else {
                result.add(station.getId());
            }
        }

        if (result.size() > 0) {
            return new ResponseStringList(1, "Success", result);
        } else {
            return new ResponseStringList(0, "No content according to name list", nameList);
        }
    }

    @Override
    public ResponseString queryById(String stationId) {
        Station station = repository.findById(stationId);
        if (station != null) {
            return new ResponseString(1, "Success", station.getName());
        } else {
            return new ResponseString(0, "No that stationId", stationId);
        }
    }

    @Override
    public ResponseStringList queryByIdBatch(List<String> idList) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            Station station = repository.findById(idList.get(i));
            if (station != null) {
                result.add(station.getName());
            }
        }

        if (result.size() > 0) {
            return new ResponseStringList(1, "Success", result);
        } else {
            return new ResponseStringList(0, "No stationNamelist according to stationIdList", result);
        }
    }
}
