package fdse.microservice.entity;


import fdse.microservice.tars.rpc.train.TrainTypeTars;

public class TrainType {

    private String id;

    private int economyClass;

    private int confortClass;

    private int averageSpeed;

    public TrainType(){

    }

    public TrainType(TrainTypeTars trainTypeTars){
        this.id = trainTypeTars.getId();
        this.economyClass = trainTypeTars.getEconomyClass();
        this.confortClass = trainTypeTars.getConfortClass();
        this.averageSpeed = trainTypeTars.getAverageSpeed();
    }

    public TrainType(String id, int economyClass, int confortClass) {
        this.id = id;
        this.economyClass = economyClass;
        this.confortClass = confortClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(int economyClass) {
        this.economyClass = economyClass;
    }

    public int getConfortClass() {
        return confortClass;
    }

    public void setConfortClass(int confortClass) {
        this.confortClass = confortClass;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public fdse.microservice.tars.basic.TrainTypeTars toTars(){
        fdse.microservice.tars.basic.TrainTypeTars trainTypeTars = new fdse.microservice.tars.basic.TrainTypeTars();
        trainTypeTars.setId(this.id);
        trainTypeTars.setAverageSpeed(this.averageSpeed);
        trainTypeTars.setConfortClass(this.confortClass);
        trainTypeTars.setEconomyClass(this.economyClass);
        return trainTypeTars;
    }

}
