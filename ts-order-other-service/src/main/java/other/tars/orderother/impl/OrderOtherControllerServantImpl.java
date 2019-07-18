package other.tars.orderother.impl;

import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import other.tars.orderother.*;
import other.tars.orderother.service.OrderOtherTarsService;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 15:47
 * @Description:
 */
@TarsServant("OrderOtherObj")
public class OrderOtherControllerServantImpl implements OrderOtherControllerServant {
    @Autowired
    private OrderOtherTarsService orderOtherTarsService;

    @Override
    public String home() {
        return "Welcome to [ Order Other Service ] !";
    }

    @Override
    public ResponseLeftTicketInfo getTicketListByDateAndTripId(SeatTars seatRequest) {
        System.out.println("[Order Other Service][Get Sold Ticket] Date:" + seatRequest.getTravelDate());
        return orderOtherTarsService.getSoldTickets(seatRequest);
    }

    @Override
    public ResponseOrder createNewOrder(OrderTars createOrder) {
        System.out.println("[Order Other Service][Create Order] Create Order form " + createOrder.getFrom() + " --->"
                + createOrder.getTo() + " at " + createOrder.getTravelDate());

        System.out.println("[Order Other Service][Verify Login] Success");
        return orderOtherTarsService.create(createOrder);
    }

    @Override
    public ResponseOrder addcreateNewOrder(OrderTars order) {
        return orderOtherTarsService.addNewOrder(order);
    }

    @Override
    public ResponseOrderList queryOrders(QueryInfoTars qi) {
        System.out.println("[Order Other Service][Query Orders] Query Orders for " + qi.getLoginId());
        return orderOtherTarsService.queryOrders(qi, qi.getLoginId());
    }

    @Override
    public ResponseOrderList queryOrdersForRefresh(QueryInfoTars qi) {
        System.out.println("[Order Other Service][Query Orders] Query Orders for " + qi.getLoginId());
        return orderOtherTarsService.queryOrdersForRefresh(qi, qi.getLoginId());

    }

    @Override
    public ResponseSoldTicket calculateSoldTicket(int travelDate, String trainNumber) {
        System.out.println("[Order Other Service][Calculate Sold Tickets] Date:" + travelDate + " TrainNumber:"
                + trainNumber);
        return orderOtherTarsService.queryAlreadySoldOrders(travelDate, trainNumber);

    }

    @Override
    public ResponseString getOrderPrice(String orderId) {
        System.out.println("[Order Other Service][Get Order Price] Order Id:" + orderId);
        return orderOtherTarsService.getOrderPrice(orderId);

    }

    @Override
    public ResponseOrder payOrder(String orderId) {
        System.out.println("[Order Other Service][Pay Order] Order Id:" + orderId);
        return orderOtherTarsService.payOrder(orderId);

    }

    @Override
    public ResponseOrder getOrderById(String orderId) {
        System.out.println("[Order Other Service][Get Order By Id] Order Id:" + orderId);
        return orderOtherTarsService.getOrderById(orderId);

    }

    @Override
    public ResponseOrder modifyOrder(String orderId, int status) {
        System.out.println("[Order Other Service][Modify Order Status] Order Id:" + orderId);
        return orderOtherTarsService.modifyOrder(orderId, status);

    }

    @Override
    public ResponseOrderSecurity securityInfoCheck(long checkDate, String accountId) {
        System.out.println("[Order Other Service][Security Info Get]");
        return orderOtherTarsService.checkSecurityAboutOrder(checkDate, accountId);

    }

    @Override
    public ResponseOrder saveOrderInfo(OrderTars orderInfo) {
        System.out.println("[Order Other Service][Verify Login] Success");
        return orderOtherTarsService.saveChanges(orderInfo);

    }

    @Override
    public ResponseOrder updateOrder(OrderTars order) {
        return orderOtherTarsService.updateOrder(order);

    }

    @Override
    public ResponseString deleteOrder(String orderId) {
        System.out.println("[Order Other Service][Delete Order] Order Id:" + orderId);
        return orderOtherTarsService.deleteOrder(orderId);

    }

    @Override
    public ResponseOrderList findAllOrder() {
        System.out.println("[Order Other Service][Find All Order]");
        return orderOtherTarsService.getAllOrders();

    }
}
