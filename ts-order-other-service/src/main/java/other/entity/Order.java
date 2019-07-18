package other.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import other.tars.orderother.OrderTars;

import java.util.Date;
import java.util.UUID;

@Document(collection = "orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    private UUID id;

    private Date boughtDate;

    private Date travelDate;

    private Date travelTime;

    //Which Account Bought it
    private UUID accountId;

    //Tickets bought for whom....
    private String contactsName;

    private int documentType;

    private String contactsDocumentNumber;

    private String trainNumber;

    private int coachNumber;

    private int seatClass;

    private String seatNumber;

    private String from;

    private String to;

    private int status;

    private String price;

    public Order(){
        boughtDate = new Date(System.currentTimeMillis());
        travelDate = new Date(123456789);
        trainNumber = "G1235";
        coachNumber = 5;
        seatClass = SeatClass.FIRSTCLASS.getCode();
        seatNumber = "5A";
        from = "shanghai";
        to = "taiyuan";
        status = OrderStatus.PAID.getCode();
        price = "0.0";
    }

    public Order(OrderTars orderTars){
        this.id=UUID.fromString(orderTars.getId());
        this.boughtDate=new Date(orderTars.getBoughtDate());
        this.travelDate=new Date(orderTars.getTravelDate());
        this.travelTime=new Date(orderTars.getTravelTime());
        this.accountId=UUID.fromString(orderTars.getAccountId());
        this.contactsName=orderTars.getContactsName();
        this.documentType=orderTars.getDocumentType();
        this.contactsDocumentNumber=orderTars.getContactsDocumentNumber();
        this.trainNumber=orderTars.getTrainNumber();
        this.coachNumber=orderTars.getCoachNumber();
        this.seatClass=orderTars.getSeatClass();
        this.seatNumber=orderTars.getSeatNumber();
        this.from=orderTars.getFrom();
        this.to=orderTars.getTo();
        this.status=orderTars.getStatus();
        this.price=orderTars.getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        return boughtDate.equals(other.getBoughtDate())
                && travelDate.equals(other.getTravelDate())
                && travelTime.equals(other.getTravelTime())
                && accountId .equals( other.getAccountId() )
                && contactsName.equals(other.getContactsName())
                && contactsDocumentNumber.equals(other.getContactsDocumentNumber())
                && documentType == other.getDocumentType()
                && trainNumber.equals(other.getTrainNumber())
                && coachNumber == other.getCoachNumber()
                && seatClass == other.getSeatClass()
                && seatNumber .equals(other.getSeatNumber())
                && from.equals(other.getFrom())
                && to.equals(other.getTo())
                && status == other.getStatus()
                && price.equals(other.price);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Date getBoughtDate() {
        return boughtDate;
    }

    public void setBoughtDate(Date boughtDate) {
        this.boughtDate = boughtDate;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public void setTravelDate(int year,int month,int day){
        Date date = new Date(year,month,day,0,0,0);
        this.travelDate = date;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }

    public void setTravelTime(int hour,int minute){
        Date date = new Date(1970,1,1,hour,minute,0);
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getCoachNumber() {
        return coachNumber;
    }

    public void setCoachNumber(int coachNumber) {
        this.coachNumber = coachNumber;
    }

    public int getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(int seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public String getContactsDocumentNumber() {
        return contactsDocumentNumber;
    }

    public void setContactsDocumentNumber(String contactsDocumentNumber) {
        this.contactsDocumentNumber = contactsDocumentNumber;
    }

    public OrderTars toTars(){
        OrderTars orderTars = new OrderTars();
        orderTars.setId(this.id.toString());
        orderTars.setBoughtDate(this.boughtDate.getTime());
        orderTars.setTravelDate(this.travelDate.getTime());
        orderTars.setTravelTime(this.travelTime.getTime());
        orderTars.setAccountId(this.accountId.toString());
        orderTars.setContactsName(this.contactsName);
        orderTars.setDocumentType(this.documentType);
        orderTars.setContactsDocumentNumber(this.contactsDocumentNumber);
        orderTars.setTrainNumber(this.trainNumber);
        orderTars.setCoachNumber(this.coachNumber);
        orderTars.setSeatClass(this.seatClass);
        orderTars.setSeatNumber(this.seatNumber);
        orderTars.setFrom(this.from);
        orderTars.setTo(this.to);
        orderTars.setStatus(this.status);
        orderTars.setPrice(this.price);
        return orderTars;
    }

}
