package other.tars.orderother.service;

import other.entity.OrderAlterInfo;
import other.entity.QueryInfo;
import other.tars.orderother.*;


/**
 * @Auther: yaya
 * @Date: 2019/7/16 15:47
 * @Description:
 */
public interface OrderOtherTarsService {
    ResponseOrder findOrderById(String id);

    ResponseOrder create(OrderTars newOrder);

    ResponseOrder updateOrder(OrderTars order);

    ResponseOrder saveChanges(OrderTars order);

    ResponseOrder cancelOrder(String accountId, String orderId);

    ResponseOrder addNewOrder(OrderTars order);

    ResponseString deleteOrder(String orderId);

    ResponseOrder getOrderById(String orderId);

    ResponseOrder payOrder(String orderId);

    ResponseString getOrderPrice(String orderId);

    ResponseOrder modifyOrder(String orderId, int status);

    ResponseOrderList getAllOrders();

    ResponseLeftTicketInfo getSoldTickets(SeatTars seatRequest);

    ResponseOrderList queryOrders(QueryInfoTars qi, String accountId);

    ResponseOrderList queryOrdersForRefresh(QueryInfoTars qi, String accountId);

    ResponseOrder alterOrder(OrderAlterInfo oai);

    ResponseSoldTicket queryAlreadySoldOrders(long travelDate, String trainNumber);

    ResponseOrderSecurity checkSecurityAboutOrder(long checkDate, String accountId);

    void initOrder(OrderTars order);
}
