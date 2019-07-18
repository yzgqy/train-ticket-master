package order.tars.order.impl;

import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import order.tars.order.*;
import order.tars.order.service.OrderTarsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 18:21
 * @Description:
 */
@TarsServant("OrderObj")
public class OrderControllerServantImpl implements OrderControllerServant {
    @Autowired
    private OrderTarsService orderTarsService;

    @Override
    public String home() {
        return "Welcome to [ Order Service ] !";
    }

    @Override
    public Response getTicketListByDateAndTripId(SeatTars seatRequest, Holder<LeftTicketInfoTars> leftTicketInfoTarsOut) {
        System.out.println("[Order Service][Get Sold Ticket] Date:" + seatRequest.getTravelDate());
        edu.fudan.common.util.Response<LeftTicketInfoTars> rs = orderTarsService.getSoldTickets(seatRequest);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        LeftTicketInfoTars out = rs.getData();
        leftTicketInfoTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response createNewOrder(OrderTars createOrder, Holder<OrderTars> orderTarsOut) {
        System.out.println("[Order Service][Create Order] Create Order form " + createOrder.getFrom() + " --->"
                + createOrder.getTo() + " at " + createOrder.getTravelDate());
        System.out.println("[Order Service][Verify Login] Success");
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.create(createOrder);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response addcreateNewOrder(OrderTars order, Holder<OrderTars> orderTarsOut) {
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.addNewOrder(order);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response queryOrders(OrderInfoTars qi, Holder<List<OrderTars>> orderTarsListOut) {
        System.out.println("[Order Other Service][Query Orders] Query Orders for " + qi.getLoginId());
        System.out.println("[Order Other Service][Verify Login] Success");
        edu.fudan.common.util.Response<List<OrderTars>> rs =orderTarsService.queryOrders(qi, qi.getLoginId());
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        List<OrderTars> out = rs.getData();
        orderTarsListOut.setValue(out);
        return response;
    }

    @Override
    public Response queryOrdersForRefresh(OrderInfoTars qi, Holder<List<OrderTars>> orderTarsListOut) {
        System.out.println("[Order Other Service][Query Orders] Query Orders for " + qi.getLoginId());
        edu.fudan.common.util.Response<List<OrderTars>> rs =orderTarsService.queryOrdersForRefresh(qi, qi.getLoginId());
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        List<OrderTars> out = rs.getData();
        orderTarsListOut.setValue(out);
        return response;
    }

    @Override
    public Response calculateSoldTicket(long travelDate, String trainNumber, Holder<SoldTicketTars> soldTicketTarsOut) {
        System.out.println("[Order Other Service][Calculate Sold Tickets] Date:" + travelDate + " TrainNumber:"
                + trainNumber);
        edu.fudan.common.util.Response<SoldTicketTars> rs =orderTarsService.queryAlreadySoldOrders(travelDate, trainNumber);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        SoldTicketTars out = rs.getData();
        soldTicketTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response getOrderPrice(String orderId, Holder<String> priceOut) {
        System.out.println("[Order Other Service][Get Order Price] Order Id:" + orderId);
        // String
        edu.fudan.common.util.Response<String> rs =orderTarsService.getOrderPrice(orderId);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        String out = rs.getData();
        priceOut.setValue(out);
        return response;
    }

    @Override
    public Response payOrder(String orderId, Holder<OrderTars> orderTarsOut) {
        System.out.println("[Order Other Service][Pay Order] Order Id:" + orderId);
        // Order
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.payOrder(orderId);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response getOrderById(String orderId, Holder<OrderTars> orderTarsOut) {
        System.out.println("[Order Other Service][Get Order By Id] Order Id:" + orderId);
        // Order
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.getOrderById(orderId);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response modifyOrder(String orderId, int status, Holder<OrderTars> orderTarsOut) {
        System.out.println("[Order Other Service][Modify Order Status] Order Id:" + orderId);
        // Order
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.modifyOrder(orderId, status);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response securityInfoCheck(long checkDate, String accountId, Holder<OrderSecurityTars> orderSecurityTarsOut) {
        System.out.println("[Order Other Service][Security Info Get]" + accountId);
        edu.fudan.common.util.Response<OrderSecurityTars> rs =orderTarsService.checkSecurityAboutOrder(checkDate, accountId);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderSecurityTars out = rs.getData();
        orderSecurityTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response saveOrderInfo(OrderTars orderInfo, Holder<OrderTars> orderTarsOut) {
        System.out.println("[Order Other Service][Verify Login] Success");
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.saveChanges(orderInfo);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response updateOrder(OrderTars order, Holder<OrderTars> orderTarsOut) {
        // Order
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.updateOrder(order);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response deleteOrder(String orderId, Holder<OrderTars> orderTarsOut) {
        System.out.println("[Order Other Service][Delete Order] Order Id:" + orderId);
        // Order
        edu.fudan.common.util.Response<OrderTars> rs =orderTarsService.deleteOrder(orderId);
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        OrderTars out = rs.getData();
        orderTarsOut.setValue(out);
        return response;
    }

    @Override
    public Response findAllOrder(Holder<List<OrderTars>> orderTarsListOut) {
        System.out.println("[Order Other Service][Find All Order]");
        // ArrayList<Order>
        edu.fudan.common.util.Response<List<OrderTars>> rs =orderTarsService.getAllOrders();
        Response response = new Response();
        response.setMsg(rs.getMsg());
        response.setStatus(rs.getStatus());
        List<OrderTars> out = rs.getData();
        orderTarsListOut.setValue(out);
        return response;
    }
}
