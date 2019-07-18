package food.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import food.tars.foodmap.FoodStoreTars;
import food.tars.foodmap.FoodTars;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "stores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodStore {

    @Id
    private UUID id;

    @NotNull
    private String stationId;

    private String storeName;

    private String telephone;

    private String businessTime;

    private double deliveryFee;

    private List<Food> foodList;

    public FoodStore(){

    }

    public FoodStore(FoodStoreTars foodStoreTars){
        this.id = UUID.fromString(foodStoreTars.getId());
        this.stationId = foodStoreTars.getStationId();
        this.storeName =foodStoreTars.getStoreName();
        this.telephone =foodStoreTars.getTelephone();
        this.businessTime = foodStoreTars.getBusinessTime();
        this.deliveryFee = foodStoreTars.getDeliveryFee();
        if(foodStoreTars.getFoodList()!=null) {
            List<Food> foods = new ArrayList<>();
            for (FoodTars foodTars : foodStoreTars.getFoodList()) {
                foods.add(new Food(foodTars));
            }
            this.foodList = foods;
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public FoodStoreTars toTars(){
        FoodStoreTars foodStoreTars = new FoodStoreTars();
        foodStoreTars.setBusinessTime(this.businessTime);
        foodStoreTars.setDeliveryFee(this.deliveryFee);
        List<Food> foods = this.foodList;
        List<FoodTars> foodTarsList = new ArrayList<>();
        if(foods!=null)
            for(Food food:foods){
                foodTarsList.add(food.toTars());
            }
        foodStoreTars.setFoodList(foodTarsList);
        foodStoreTars.setId(this.id.toString());
        foodStoreTars.setStationId(this.stationId);
        foodStoreTars.setStoreName(this.storeName);
        foodStoreTars.setTelephone(this.telephone);
        return foodStoreTars;
    }

}
