package food.tars.foodmap.service;

import food.entity.FoodStore;
import food.entity.TrainFood;
import food.repository.FoodStoreRepository;
import food.repository.TrainFoodRepository;
import food.tars.foodmap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 18:03
 * @Description:
 */
@Service
public class FoodMapTarsServiceImpl implements FoodMapTarsService{
    @Autowired
    FoodStoreRepository foodStoreRepository;

    @Autowired
    TrainFoodRepository trainFoodRepository;

    @Override
    public ResponseFoodStore createFoodStore(FoodStoreTars fs) {
        FoodStore fsTemp = foodStoreRepository.findById(UUID.fromString(fs.getId()));
        if (fsTemp != null) {
            System.out.println("[Food Map Service][Init FoodStore] Already Exists Id:" + fs.getId());
            return new ResponseFoodStore(0, "Already Exists Id", null);
        } else {
            foodStoreRepository.save(new FoodStore(fs));
            return new ResponseFoodStore(1, "Save Success", fs);
        }
    }

    @Override
    public TrainFoodTars createTrainFood(TrainFoodTars tf) {
        TrainFood tfTemp = trainFoodRepository.findById(UUID.fromString(tf.getId()));
        if (tfTemp != null) {
            System.out.println("[Food Map Service][Init TrainFood] Already Exists Id:" + tf.getId());
        } else {
            trainFoodRepository.save(new TrainFood(tf));
        }
        return tf;
    }

    @Override
    public ResponseFoodStores listFoodStores() {
        List<FoodStore> foodStores = foodStoreRepository.findAll();
        List<FoodStoreTars> foodStoreTarsList = new ArrayList<>();
        if (foodStores != null && foodStores.size() > 0) {
            for(FoodStore foodStore:foodStores){
                foodStoreTarsList.add(foodStore.toTars());
            }
            return new ResponseFoodStores(1, "Success", foodStoreTarsList);
        } else {
            return new ResponseFoodStores(0, "Foodstore is empty", null);
        }
    }

    @Override
    public ResponseTrainFoods listTrainFood() {
        List<TrainFood> trainFoodList = trainFoodRepository.findAll();
        List<TrainFoodTars> trainFoodTarsList = new ArrayList<>();
        if (trainFoodList != null && trainFoodList.size() > 0) {
            for(TrainFood trainFood:trainFoodList){
                trainFoodTarsList.add(trainFood.toTars());
            }
            return new ResponseTrainFoods(1, "Success", trainFoodTarsList);
        } else {
            return new ResponseTrainFoods(0, "No content", null);
        }
    }

    @Override
    public ResponseFoodStores listFoodStoresByStationId(String stationId) {
        List<FoodStore> foodStoreList = foodStoreRepository.findByStationId(stationId);
        List<FoodStoreTars> foodStoreTarsList = new ArrayList<>();
        if (foodStoreList != null && foodStoreList.size() > 0) {
            for(FoodStore foodStore:foodStoreList){
                foodStoreTarsList.add(foodStore.toTars());
            }
            return new ResponseFoodStores(1, "Success", foodStoreTarsList);
        } else {
            return new ResponseFoodStores(0, "FoodStore is empty", null);
        }
    }

    @Override
    public ResponseTrainFoods listTrainFoodByTripId(String tripId) {
        List<TrainFood> trainFoodList = trainFoodRepository.findByTripId(tripId);
        List<TrainFoodTars> trainFoodTarsList = new ArrayList<>();
        if (trainFoodList != null) {
            for(TrainFood trainFood:trainFoodList){
                trainFoodTarsList.add(trainFood.toTars());
            }
            return new ResponseTrainFoods(1, "Success", trainFoodTarsList);
        } else {
            return new ResponseTrainFoods(0, "No content", null);
        }
    }

    @Override
    public ResponseFoodStores getFoodStoresByStationIds(List<String> stationIds) {
        List<FoodStore> foodStoreList = foodStoreRepository.findByStationIdIn(stationIds);
        List<FoodStoreTars> foodStoreTarsList = new ArrayList<>();
        if (foodStoreList != null) {
            for(FoodStore foodStore:foodStoreList){
                foodStoreTarsList.add(foodStore.toTars());
            }
            return new ResponseFoodStores(1, "Success", foodStoreTarsList);
        } else {
            return new ResponseFoodStores(0, "No content", foodStoreTarsList);
        }
    }
}
