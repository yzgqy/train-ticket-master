package adminorder.tars.adminorder.service;

import adminorder.tars.adminorder.OrderTars;
import adminorder.tars.rpc.order.OrderControllerPrx;
import adminorder.tars.rpc.orderother.OrderOtherControllerPrx;
import adminorder.tars.rpc.orderother.ResponseOrder;
import adminorder.tars.rpc.orderother.ResponseOrderList;
import adminorder.tars.rpc.orderother.ResponseString;
import com.qq.tars.common.support.Holder;
import edu.fudan.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 16:52
 * @Description:
 */
@Slf4j
@Service
public class AdminOrderTarsServiceImpl implements AdminOrderTarsService {
    @Autowired
    private OrderOtherControllerPrx orderOtherControllerPrx;

    @Autowired
    private OrderControllerPrx orderControllerPrx;

    @Override
    public Response<List<OrderTars>> getAllOrders() {
        System.out.println("[Admin Order Service][Get All Orders]");
        //Get all of the orders
        ArrayList<OrderTars> orders = new ArrayList<OrderTars>();
        //From ts-order-service1
        Holder<List< adminorder.tars.rpc.order.OrderTars >> orderTarsListOut =new Holder<>();
        adminorder.tars.rpc.order.Response rp = orderControllerPrx.findAllOrder(orderTarsListOut);

        if (rp.getStatus() == 1) {
            System.out.println("[Admin Order Service][Get Orders From ts-order-service1 successfully!]");
            for(adminorder.tars.rpc.order.OrderTars orders1: orderTarsListOut.getValue())
                orders.add(new OrderTars(orders1));
        } else {
            System.out.println("[Admin Order Service][Get Orders From ts-order-service1 fail!]");
        }

        //From ts-order-other-service1
        ResponseOrderList rp2 = orderOtherControllerPrx.findAllOrder();
        if (rp2.getStatus() == 1) {
            System.out.println("[Admin Order Service][Get Orders From ts-order-other-service1 successfully!]");
            for(adminorder.tars.rpc.orderother.OrderTars orders2: rp2.getData())
                orders.add(new OrderTars(orders2));
        } else {
            System.out.println("[Admin Order Service][Get Orders From ts-order-other-service1 fail!]");
        }

        //Return orders
        return new Response<>(1, "Get the orders successfully!", orders);

    }

    @Override
    public Response<OrderTars> deleteOrder(String orderId, String trainNumber) {
        Response<OrderTars> deleteOrderResult = new Response<>();
        if (trainNumber.startsWith("G") || trainNumber.startsWith("D")) {
            System.out.println("[Admin Order Service][Delete Order]");
            Holder<adminorder.tars.rpc.order.OrderTars> orderTarsOut = new Holder<>();
            adminorder.tars.rpc.order.Response rp1 = orderControllerPrx.deleteOrder(orderId,orderTarsOut);
            deleteOrderResult.setData(new OrderTars(orderTarsOut.getValue()));
            deleteOrderResult.setMsg(rp1.getMsg());
            deleteOrderResult.setStatus(rp1.getStatus());
        } else {
            System.out.println("[Admin Order Service][Delete Order Other]");
            ResponseString rp2 = orderOtherControllerPrx.deleteOrder(orderId);
            deleteOrderResult.setData(null);
            deleteOrderResult.setMsg(rp2.getMsg());
            deleteOrderResult.setStatus(rp2.getStatus());
        }
        return deleteOrderResult;
    }

    @Override
    public Response<OrderTars> updateOrder(OrderTars request) {
        Response<OrderTars> updateOrderResult = new Response<>();
        log.info("UPDATE ORDER INFO : " + request.toString());
        if (request.getTrainNumber().startsWith("G") || request.getTrainNumber().startsWith("D")) {
            System.out.println("[Admin Order Service][Update Order]");
            Holder<adminorder.tars.rpc.order.OrderTars> orderTarsOut = new Holder<>();
            adminorder.tars.rpc.order.Response rp1 = orderControllerPrx.updateOrder(new adminorder.tars.rpc.order.OrderTars(request),orderTarsOut);
            updateOrderResult.setData(new OrderTars(orderTarsOut.getValue()));
            updateOrderResult.setMsg(rp1.getMsg());
            updateOrderResult.setStatus(rp1.getStatus());

        } else {
            System.out.println("[Admin Order Service][Delete Order Other]");
            ResponseOrder rp2 = orderOtherControllerPrx.updateOrder(new adminorder.tars.rpc.orderother.OrderTars(request));
            updateOrderResult.setData(new OrderTars(rp2.getData()));
            updateOrderResult.setMsg(rp2.getMsg());
            updateOrderResult.setStatus(rp2.getStatus());
        }
        return updateOrderResult;
    }

    @Override
    public Response<OrderTars> addOrder(OrderTars request) {

        Response addOrderResult = new Response<>();
        if (request.getTrainNumber().startsWith("G") || request.getTrainNumber().startsWith("D")) {
            System.out.println("[Admin Order Service][Add New Order]");
            Holder<adminorder.tars.rpc.order.OrderTars> orderTarsOut = new Holder<>();
            adminorder.tars.rpc.order.Response rp1 = orderControllerPrx.addcreateNewOrder(new adminorder.tars.rpc.order.OrderTars(request),orderTarsOut);
            addOrderResult.setData(new OrderTars(orderTarsOut.getValue()));
            addOrderResult.setMsg(rp1.getMsg());
            addOrderResult.setStatus(rp1.getStatus());

        } else {
            System.out.println("[Admin Order Service][Add New Order Other]");
            ResponseOrder rp2 = orderOtherControllerPrx.addcreateNewOrder(new adminorder.tars.rpc.orderother.OrderTars(request));
            addOrderResult.setData(new OrderTars(rp2.getData()));
            addOrderResult.setMsg(rp2.getMsg());
            addOrderResult.setStatus(rp2.getStatus());
        }
        return addOrderResult;
    }
}
