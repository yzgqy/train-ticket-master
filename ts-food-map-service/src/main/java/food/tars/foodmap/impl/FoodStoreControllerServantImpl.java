package food.tars.foodmap.impl;

import com.qq.tars.spring.annotation.TarsServant;
import food.tars.foodmap.FoodStoreControllerServant;
import food.tars.foodmap.ResponseFoodStores;
import food.tars.foodmap.service.FoodMapTarsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/12 18:01
 * @Description:
 */
@TarsServant("FoodStoreObj")
public class FoodStoreControllerServantImpl implements FoodStoreControllerServant {
    @Autowired
    private FoodMapTarsService foodMapTarsService;

    @Override
    public String home() {
        return "Welcome to [ Food store Service ] !";
    }

    @Override
    public ResponseFoodStores getAllFoodStores() {
        System.out.println("[Food Map Service][Get All FoodStores]");
        return foodMapTarsService.listFoodStores();
    }

    @Override
    public ResponseFoodStores getFoodStoresOfStation(String stationId) {
        System.out.println("[Food Map Service][Get FoodStores By StationId]");
        return foodMapTarsService.listFoodStoresByStationId(stationId);

    }

    @Override
    public ResponseFoodStores getFoodStoresByStationIds(List<String> stationIdList) {
        return foodMapTarsService.getFoodStoresByStationIds(stationIdList);

    }
}
