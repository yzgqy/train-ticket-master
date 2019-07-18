package foodsearch.tars.food.impl;

import com.qq.tars.spring.annotation.TarsServant;
import foodsearch.tars.food.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 10:38
 * @Description:
 */
@TarsServant("FoodObj")
public class FoodControllerServantImpl implements FoodControllerServant {
    @Override
    public String home() {
        return null;
    }

    @Override
    public ResponseFoodOrderList findAllFoodOrder() {
        return null;
    }

    @Override
    public ResponseFoodOrder createFoodOrder(FoodOrderTars addFoodOrder) {
        return null;
    }

    @Override
    public ResponseFoodOrder updateFoodOrder(FoodOrderTars updateFoodOrder) {
        return null;
    }

    @Override
    public ResponseFoodOrder deleteFoodOrder(String orderId) {
        return null;
    }

    @Override
    public ResponseFoodOrder findFoodOrderByOrderId(String orderId) {
        return null;
    }

    @Override
    public ResponseAllTripFoodList getAllFood(String date, String startStation, String endStation, String tripId) {
        return null;
    }
}
