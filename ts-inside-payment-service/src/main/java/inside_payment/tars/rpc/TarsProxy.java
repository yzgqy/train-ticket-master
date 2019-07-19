package inside_payment.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import inside_payment.tars.rpc.order.OrderControllerPrx;
import inside_payment.tars.rpc.orderother.OrderOtherControllerPrx;
import inside_payment.tars.rpc.payment.PaymentControllerPrx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 15:12
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("OrderPrx")
    public OrderControllerPrx getOrderPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        OrderControllerPrx proxy = communicator.stringToProxy(OrderControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

    @Bean("OrderOtherPrx")
    public OrderOtherControllerPrx getOrderOtherPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        OrderOtherControllerPrx proxy = communicator.stringToProxy(OrderOtherControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

    @Bean("PaymentPrx")
    public PaymentControllerPrx getPaymentPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        PaymentControllerPrx proxy = communicator.stringToProxy(PaymentControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}

