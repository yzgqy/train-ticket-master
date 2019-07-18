package train.tars.train.impl;

import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import train.entity.TrainType;
import train.tars.train.ResponseTrainType;
import train.tars.train.ResponseTrainTypes;
import train.tars.train.TrainControllerServant;
import train.tars.train.TrainTypeTars;
import train.tars.train.service.TrainTarsService;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 16:54
 * @Description:
 */
@TarsServant("TrainObj")
public class TrainControllerServantImpl implements TrainControllerServant {
    @Autowired
    private TrainTarsService trainTarsService;

    @Override
    public String home() {
        return "Welcome to [ Train Service ] !";
    }

    @Override
    public ResponseTrainType create(TrainTypeTars trainType) {
        boolean isCreateSuccess = trainTarsService.create(trainType);
        if (isCreateSuccess) {
            return new ResponseTrainType(1, "create success", trainType);
        } else {
            return new ResponseTrainType(0, "train type already exist", trainType);
        }
    }

    @Override
    public ResponseTrainType retrieve(String id) {
        TrainTypeTars trainType = trainTarsService.retrieve(id);
        if (trainType == null) {
            return new ResponseTrainType(0, "here is no TrainType with the trainType id", null);
        } else {
            return new ResponseTrainType(1, "success", trainType);
        }
    }

    @Override
    public ResponseTrainType update(TrainTypeTars trainType) {
        boolean isUpdateSuccess = trainTarsService.update(trainType);
        if (isUpdateSuccess) {
            return new ResponseTrainType(1, "update success", trainType);
        } else {
            return new ResponseTrainType(0, "there is no trainType with the trainType id", trainType);
        }
    }

    @Override
    public ResponseTrainType delete(String id) {
        boolean isDeleteSuccess = trainTarsService.delete(id);
        if (isDeleteSuccess) {
            return new ResponseTrainType(1, "delete success", null);
        } else {
            return new ResponseTrainType(0, "there is no train according to id", null);
        }
    }

    @Override
    public ResponseTrainTypes query() {
        List<TrainTypeTars> trainTypes = trainTarsService.query();
        if (trainTypes != null && trainTypes.size() > 0) {
            return new ResponseTrainTypes(1, "success", trainTypes);
        } else {
            return new ResponseTrainTypes(0, "no content", trainTypes);
        }
    }
}
