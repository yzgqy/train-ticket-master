package food.tars.foodmap.service;

import food.tars.foodmap.*;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 18:03
 * @Description:
 */
public interface FoodMapTarsService {
    // create data
    ResponseFoodStore createFoodStore(FoodStoreTars fs);

    TrainFoodTars createTrainFood(TrainFoodTars tf);

    // query all food
    ResponseFoodStores listFoodStores();

    ResponseTrainFoods listTrainFood();

    // query according id
    ResponseFoodStores listFoodStoresByStationId(String stationId);

    ResponseTrainFoods listTrainFoodByTripId(String tripId);

    ResponseFoodStores getFoodStoresByStationIds(List<String> stationIds);

}
