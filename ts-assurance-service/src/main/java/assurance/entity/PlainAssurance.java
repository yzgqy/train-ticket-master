package assurance.entity;


import assurance.tars.assurance.PlainAssuranceTars;

import java.io.Serializable;
import java.util.UUID;

public class PlainAssurance implements Serializable {

    private UUID id;

    private UUID orderId;

    private  int typeIndex;

    private String typeName;

    private double typePrice;

    public PlainAssurance(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(double typePrice) {
        this.typePrice = typePrice;
    }

    public PlainAssuranceTars toTars (){
        PlainAssuranceTars plainAssuranceTars = new PlainAssuranceTars();
        plainAssuranceTars.setId(this.id.toString());
        plainAssuranceTars.setOrderId(this.orderId.toString());
        plainAssuranceTars.setTypeIndex(this.typeIndex);
        plainAssuranceTars.setTypeName(this.typeName);
        plainAssuranceTars.setTypePrice(this.typePrice);
        return plainAssuranceTars;
    }

}
