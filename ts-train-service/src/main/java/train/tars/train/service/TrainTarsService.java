package train.tars.train.service;

import train.tars.train.TrainTypeTars;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 16:54
 * @Description:
 */
public interface TrainTarsService {
    //CRUD
    boolean create(TrainTypeTars trainType);

    TrainTypeTars retrieve(String id);

    boolean update(TrainTypeTars trainType);

    boolean delete(String id);

    List<TrainTypeTars> query();
}
