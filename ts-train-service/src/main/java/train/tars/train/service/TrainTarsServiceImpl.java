package train.tars.train.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import train.entity.TrainType;
import train.repository.TrainTypeRepository;
import train.tars.train.TrainTypeTars;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 16:55
 * @Description:
 */
@Service
public class TrainTarsServiceImpl implements TrainTarsService{
    @Autowired
    private TrainTypeRepository repository;

    @Override
    public boolean create(TrainTypeTars trainType) {
        boolean result = false;
        if (repository.findById(trainType.getId()) == null) {
            TrainType type = new TrainType(trainType.getId(), trainType.getEconomyClass(), trainType.getConfortClass());
            type.setAverageSpeed(trainType.getAverageSpeed());
            repository.save(type);
            result = true;
        }
        return result;
    }

    @Override
    public TrainTypeTars retrieve(String id) {
        if (repository.findById(id) == null) {
            //log.info("ts-train-service1:retireve "+id+ " and there is no TrainType with the id:" +id);
            return null;
        } else {
            return repository.findById(id).toTars();
        }
    }

    @Override
    public boolean update(TrainTypeTars trainType) {
        boolean result = false;
        //
        if (repository.findById(trainType.getId()) != null) {
            TrainType type = new TrainType(trainType.getId(), trainType.getEconomyClass(), trainType.getConfortClass());
            type.setAverageSpeed(trainType.getAverageSpeed());
            repository.save(type);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (repository.findById(id) == null) {
            //log.info("ts-train-service1:delete " + id +" and there doesn't exist TrainType with the id:" +id);
        } else {
            repository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<TrainTypeTars> query() {
        List<TrainType> trainTypes = repository.findAll();
        List<TrainTypeTars> trainTypeTarsList =  new ArrayList<>();
        if(trainTypes!=null) {
            for (TrainType type : trainTypes) {
                trainTypeTarsList.add(type.toTars());
            }
        }
        return trainTypeTarsList;
    }
}
