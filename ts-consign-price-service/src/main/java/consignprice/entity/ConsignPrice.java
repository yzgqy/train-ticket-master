package consignprice.entity;

import consignprice.tars.consignprice.ConsignPriceTars;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "consign_price")
public class ConsignPrice {
    @Id
    private UUID id;
    private int index;
    private double initialWeight;
    private double initialPrice;
    private double withinPrice;
    private double beyondPrice;

    public ConsignPrice(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(double initialWeight) {
        this.initialWeight = initialWeight;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getWithinPrice() {
        return withinPrice;
    }

    public void setWithinPrice(double withinPrice) {
        this.withinPrice = withinPrice;
    }

    public double getBeyondPrice() {
        return beyondPrice;
    }

    public void setBeyondPrice(double beyondPrice) {
        this.beyondPrice = beyondPrice;
    }

    public ConsignPriceTars toTars (){
        ConsignPriceTars consignPriceTars = new ConsignPriceTars();
        consignPriceTars.setBeyondPrice(this.beyondPrice);
        consignPriceTars.setId(this.id.toString());
        consignPriceTars.setIndex(this.index);
        consignPriceTars.setInitialPrice(this.initialPrice);
        consignPriceTars.setInitialWeight(this.initialWeight);
        consignPriceTars.setWithinPrice(this.withinPrice);
        return consignPriceTars;
    }
}
