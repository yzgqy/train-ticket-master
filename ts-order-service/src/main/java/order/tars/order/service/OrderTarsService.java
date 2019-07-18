package order.tars.order.service;

import order.entity.OrderAlterInfo;
import order.tars.order.*;
import edu.fudan.common.util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 18:21
 * @Description:
 */
public interface OrderTarsService {
    Response<OrderTars> findOrderById(UUID id);

    Response<OrderTars> create(OrderTars newOrder);

    Response<OrderTars> saveChanges(OrderTars order);

    Response<OrderTars> cancelOrder(UUID accountId, UUID orderId);

    Response<List<OrderTars>> queryOrders(OrderInfoTars qi, String accountId);

    Response<List<OrderTars>> queryOrdersForRefresh(OrderInfoTars qi, String accountId);

    Response<OrderTars> alterOrder(OrderAlterInfo oai);

    Response<SoldTicketTars> queryAlreadySoldOrders(long travelDate, String trainNumber);

    Response<List<OrderTars>> getAllOrders();

    Response<OrderTars> modifyOrder(String orderId, int status);

    Response<String> getOrderPrice(String orderId);

    Response<OrderTars> payOrder(String orderId);

    Response<OrderTars> getOrderById(String orderId);

    Response<OrderSecurityTars> checkSecurityAboutOrder(long checkDate, String accountId);

    void initOrder(OrderTars order);

    Response<OrderTars> deleteOrder(String orderId);

    Response<LeftTicketInfoTars> getSoldTickets(SeatTars seatRequest);

    Response<OrderTars> addNewOrder(OrderTars order);

    Response<OrderTars> updateOrder(OrderTars order);
}
