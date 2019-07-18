package execute.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import execute.tars.rpc.order.OrderControllerPrx;
import execute.tars.rpc.orderother.OrderOtherControllerPrx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 17:49
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
