package food.tars.foodmap.impl;

import com.qq.tars.spring.annotation.TarsServant;
import food.tars.foodmap.ResponseTrainFoods;
import food.tars.foodmap.TrainFoodControllerServant;
import food.tars.foodmap.service.FoodMapTarsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 18:01
 * @Description:
 */
@TarsServant("TrainFoodObj")
public class TrainFoodControllerServantImpl implements TrainFoodControllerServant {
    @Autowired
    private FoodMapTarsService foodMapTarsService;

    @Override
    public String home() {
        return "Welcome to [ Train Food Service ] !";
    }

    @Override
    public ResponseTrainFoods getAllTrainFood() {
        System.out.println("[Food Map Service][Get All TrainFoods]");
        return foodMapTarsService.listTrainFood();
    }

    @Override
    public ResponseTrainFoods getTrainFoodOfTrip(String tripId) {
        System.out.println("[Food Map Service][Get TrainFoods By TripId]");
        return foodMapTarsService.listTrainFoodByTripId(tripId);
    }
}
