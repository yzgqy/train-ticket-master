package adminorder.tars.adminorder.impl;

import adminorder.tars.adminorder.AdminOrderControllerServant;
import adminorder.tars.adminorder.OrderTars;
import adminorder.tars.adminorder.Response;
import adminorder.tars.adminorder.service.AdminOrderTarsService;
import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 16:49
 * @Description:
 */
@TarsServant("AdminOrderObj")
public class AdminOrderControllerServantImpl implements AdminOrderControllerServant {
    @Autowired
    AdminOrderTarsService adminOrderTarsService;
    @Override
    public String home() {
        return "Welcome to [ AdminOrder Service ] !";
    }

    @Override
    public Response getAllOrders(Holder<List<OrderTars>> outData) {
        edu.fudan.common.util.Response<List<OrderTars>> rp = adminOrderTarsService.getAllOrders();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response addOrder(OrderTars request, Holder<OrderTars> outData) {
        edu.fudan.common.util.Response<OrderTars> rp = adminOrderTarsService.addOrder(request);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response updateOrder(OrderTars request, Holder<OrderTars> outData) {
       edu.fudan.common.util.Response<OrderTars> rp = adminOrderTarsService.updateOrder(request);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response deleteOrder(String orderId, String trainNumber, Holder<OrderTars> outData) {
       edu.fudan.common.util.Response<OrderTars> rp = adminOrderTarsService.deleteOrder(orderId, trainNumber);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }
}
