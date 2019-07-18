package food.entity;


import food.tars.foodmap.FoodTars;

import java.io.Serializable;

public class Food implements Serializable{

    private String foodName;
    private double price;
    public Food(){

    }
    public Food(FoodTars foodTars){
        this.foodName = foodTars.getFoodName();
        this.price = foodTars.getPrice();

    }
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FoodTars toTars(){
        FoodTars foodTars = new FoodTars();
        foodTars.setFoodName(this.foodName);
        foodTars.setPrice(this.price);
        return foodTars;
    }

}
