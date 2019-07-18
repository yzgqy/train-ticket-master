package adminorder.tars.adminorder.service;

import adminorder.entity.Order;
import adminorder.tars.adminorder.OrderTars;
import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 16:50
 * @Description:
 */
public interface AdminOrderTarsService {
    Response<List<OrderTars>> getAllOrders();

    Response<OrderTars> deleteOrder(String orderId,String trainNumber);
    Response<OrderTars> updateOrder(OrderTars request);
    Response<OrderTars> addOrder(OrderTars request);
}
