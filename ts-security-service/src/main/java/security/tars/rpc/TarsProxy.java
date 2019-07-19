package security.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import security.tars.rpc.order.OrderControllerPrx;
import security.tars.rpc.orderother.OrderOtherControllerPrx;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 10:55
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("OrderControllerPrx")
    public OrderControllerPrx getOrderControllerPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        OrderControllerPrx proxy = communicator.stringToProxy(OrderControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

    @Bean("OrderOtherControllerPrx")
    public OrderOtherControllerPrx getOrderOtherControllerPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        OrderOtherControllerPrx proxy = communicator.stringToProxy(OrderOtherControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}