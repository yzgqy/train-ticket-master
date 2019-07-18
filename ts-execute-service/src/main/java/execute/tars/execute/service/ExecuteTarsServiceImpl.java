package execute.tars.execute.service;

import com.qq.tars.common.support.Holder;
import edu.fudan.common.util.Response;
import execute.entity.OrderStatus;
import execute.tars.rpc.order.OrderControllerPrx;
import execute.tars.rpc.order.OrderTars;
import execute.tars.rpc.orderother.OrderOtherControllerPrx;
import execute.tars.rpc.orderother.ResponseOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 17:47
 * @Description:
 */
@Service
@Slf4j
public class ExecuteTarsServiceImpl implements ExecuteTarsService{
    @Autowired
    private OrderOtherControllerPrx orderOtherControllerPrx;

    @Autowired
    private OrderControllerPrx orderControllerPrx;

    @Override
    public Response ticketExecute(String orderId) {
        //1.获取订单信息

        Response<OrderTars> resultFromOrder = getOrderByIdFromOrder(orderId);
        //  TicketExecuteResult result = new TicketExecuteResult();
        OrderTars order;
        if (resultFromOrder.getStatus() == 1) {
            order =   resultFromOrder.getData();
            //2.检查订单是否可以进站
            if (order.getStatus() != OrderStatus.COLLECTED.getCode()) {
                return new Response<>(0, "Order Status Wrong", null);
            }
            //3.确认进站 请求修改订单信息

            Response resultExecute = executeOrder(orderId, OrderStatus.USED.getCode());
            if (resultExecute.getStatus() == 1) {
                return new Response<>(1, "Success.", null);
            } else {
                return new Response<>(0, resultExecute.getMsg(), null);
            }
        } else {
            resultFromOrder = getOrderByIdFromOrderOther(orderId);
            if (resultFromOrder.getStatus() == 1) {
                order =   resultFromOrder.getData();
                //2.检查订单是否可以进站
                if (order.getStatus() != OrderStatus.COLLECTED.getCode()) {
                    return new Response<>(0, "Order Status Wrong", null);
                }
                //3.确认进站 请求修改订单信息

                Response resultExecute = executeOrderOther(orderId, OrderStatus.USED.getCode());
                if (resultExecute.getStatus() == 1) {
                    return new Response<>(1, "Success", null);
                } else {
                    return new Response<>(0, resultExecute.getMsg(), null);
                }
            } else {
                return new Response<>(0, "Order Not Found", null);
            }
        }
    }

    @Override
    public Response ticketCollect(String orderId) {
        //1.获取订单信息

        Response<OrderTars> resultFromOrder = getOrderByIdFromOrder(orderId);
        // TicketExecuteResult result = new TicketExecuteResult();
        OrderTars order;
        if (resultFromOrder.getStatus() == 1) {
            order =  resultFromOrder.getData();
            //2.检查订单是否可以进站
            if (order.getStatus() != OrderStatus.PAID.getCode() && order.getStatus() != OrderStatus.CHANGE.getCode()) {
                return new Response<>(0, "Order Status Wrong", null);
            }
            //3.确认进站 请求修改订单信息

            Response resultExecute = executeOrder(orderId, OrderStatus.COLLECTED.getCode());
            if (resultExecute.getStatus() == 1) {
                return new Response<>(1, "Success", null);
            } else {
                return new Response<>(0, resultExecute.getMsg(), null);
            }
        } else {
            resultFromOrder = getOrderByIdFromOrderOther(orderId);
            if (resultFromOrder.getStatus() == 1) {
                order = resultFromOrder.getData();
                //2.检查订单是否可以进站
                if (order.getStatus() != OrderStatus.PAID.getCode() && order.getStatus() != OrderStatus.CHANGE.getCode()) {
                    return new Response<>(0, "Order Status Wrong", null);
                }
                //3.确认进站 请求修改订单信息
                Response resultExecute = executeOrderOther(orderId, OrderStatus.COLLECTED.getCode());
                if (resultExecute.getStatus() == 1) {
                    return new Response<>(1, "Success.", null);
                } else {
                    return new Response<>(0, resultExecute.getMsg(), null);
                }
            } else {
                return new Response<>(0, "Order Not Found", null);
            }
        }
    }

    private Response<OrderTars> getOrderByIdFromOrder(String orderId) {

        System.out.println("[Execute Service][Get Order] Getting....");
        Response<OrderTars> response = new Response<>();
        Holder<OrderTars> orderTarsOut = new Holder<>();
        execute.tars.rpc.order.Response rp1 = orderControllerPrx.getOrderById(orderId,orderTarsOut);
        response.setStatus(rp1.getStatus());
        response.setMsg(rp1.getMsg());
        response.setData(orderTarsOut.getValue());
        return response;
    }

    private Response executeOrder(String orderId, int status) {
        System.out.println("[Execute Service][Execute Order] Executing....");
        Response<OrderTars> response = new Response<>();
        Holder<OrderTars> orderTarsOut = new Holder<>();
        execute.tars.rpc.order.Response rp1 = orderControllerPrx.modifyOrder(orderId,status,orderTarsOut);
        response.setStatus(rp1.getStatus());
        response.setMsg(rp1.getMsg());
        response.setData(orderTarsOut.getValue());
        return response;
    }

    private Response<OrderTars> getOrderByIdFromOrderOther(String orderId) {
        System.out.println("[Execute Service][Get Order] Getting....");
        Response<OrderTars> response = new Response<>();
        ResponseOrder rp2 = orderOtherControllerPrx.getOrderById(orderId);
        response.setStatus(rp2.getStatus());
        response.setMsg(rp2.getMsg());
        response.setData(new OrderTars(rp2.getData()));
        return response;
    }

    private Response executeOrderOther(String orderId, int status) {
        System.out.println("[Execute Service][Execute Order] Executing....");
        Response<OrderTars> response = new Response<>();
        ResponseOrder rp2 = orderOtherControllerPrx.modifyOrder(orderId,status);
        response.setStatus(rp2.getStatus());
        response.setMsg(rp2.getMsg());
        response.setData(new OrderTars(rp2.getData()));
        return response;
    }
}
