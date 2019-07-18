package food.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import food.tars.foodmap.FoodTars;
import food.tars.foodmap.TrainFoodTars;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "trainfoods")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainFood {

    @Id
    private UUID id;

    @NotNull
    private String tripId;

    private List<Food> foodList;

    public TrainFood(){

    }

    public TrainFood(TrainFoodTars trainFoodTars){
        this.id = UUID.fromString(trainFoodTars.getId());
        this.tripId = trainFoodTars.getTripId();
        if(trainFoodTars.getFoodList()!=null) {
            List<Food> foods = new ArrayList<>();
            for (FoodTars foodTars : trainFoodTars.getFoodList()) {
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

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public TrainFoodTars toTars(){
        TrainFoodTars trainFoodTars = new TrainFoodTars();
        trainFoodTars.setId(this.id.toString());
        trainFoodTars.setTripId(this.tripId);
        List<Food> foods = this.foodList;
        List<FoodTars> foodTarsList = new ArrayList<>();
        if(foods!=null) {
            for (Food food : foods)
                foodTarsList.add(food.toTars());
        }
        trainFoodTars.setFoodList(foodTarsList);

        return trainFoodTars;
    }

}
