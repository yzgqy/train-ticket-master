package foodsearch.tars.food.service;

import foodsearch.tars.food.FoodOrderTars;
import foodsearch.tars.food.ResponseAllTripFoodList;
import foodsearch.tars.food.ResponseFoodOrder;
import foodsearch.tars.food.ResponseFoodOrderList;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 10:39
 * @Description:
 */
public interface FoodTarsService {

    ResponseFoodOrder createFoodOrder(FoodOrderTars afoi);

    ResponseFoodOrder deleteFoodOrder(String orderId);

    ResponseFoodOrder findByOrderId(String orderId);

    ResponseFoodOrder updateFoodOrder(FoodOrderTars updateFoodOrder);

    ResponseFoodOrderList findAllFoodOrder();

    ResponseAllTripFoodList getAllFood(String date, String startStation, String endStation, String tripId);

}
