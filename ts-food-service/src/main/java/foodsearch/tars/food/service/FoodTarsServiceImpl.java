package foodsearch.tars.food.service;

import foodsearch.tars.food.FoodOrderTars;
import foodsearch.tars.food.ResponseAllTripFoodList;
import foodsearch.tars.food.ResponseFoodOrder;
import foodsearch.tars.food.ResponseFoodOrderList;
import org.springframework.stereotype.Service;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 10:39
 * @Description:
 */
@Service
public class FoodTarsServiceImpl implements FoodTarsService{
    @Override
    public ResponseFoodOrder createFoodOrder(FoodOrderTars afoi) {
        return null;
    }

    @Override
    public ResponseFoodOrder deleteFoodOrder(String orderId) {
        return null;
    }

    @Override
    public ResponseFoodOrder findByOrderId(String orderId) {
        return null;
    }

    @Override
    public ResponseFoodOrder updateFoodOrder(FoodOrderTars updateFoodOrder) {
        return null;
    }

    @Override
    public ResponseFoodOrderList findAllFoodOrder() {
        return null;
    }

    @Override
    public ResponseAllTripFoodList getAllFood(String date, String startStation, String endStation, String tripId) {
        return null;
    }
}
