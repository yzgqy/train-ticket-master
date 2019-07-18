package order.entity;

import order.tars.order.OrderSecurityTars;

public class OrderSecurity {

    private int orderNumInLastOneHour;

    private int orderNumOfValidOrder;

    public OrderSecurity() {
        //Default Constructor
    }

    public int getOrderNumInLastOneHour() {
        return orderNumInLastOneHour;
    }

    public void setOrderNumInLastOneHour(int orderNumInLastOneHour) {
        this.orderNumInLastOneHour = orderNumInLastOneHour;
    }

    public int getOrderNumOfValidOrder() {
        return orderNumOfValidOrder;
    }

    public void setOrderNumOfValidOrder(int orderNumOfValidOrder) {
        this.orderNumOfValidOrder = orderNumOfValidOrder;
    }

    public OrderSecurityTars toTars(){
        OrderSecurityTars orderSecurityTars = new OrderSecurityTars();
        orderSecurityTars.setOrderNumInLastOneHour(this.orderNumInLastOneHour);
        orderSecurityTars.setOrderNumOfValidOrder(this.orderNumOfValidOrder);
        return orderSecurityTars;
    }
}
