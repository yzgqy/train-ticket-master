package adminroute.tars.rpc;

import adminroute.tars.rpc.route.RouteControllerPrx;
import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 15:46
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("RouteControllerPrx")
    public RouteControllerPrx getRoutePrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        RouteControllerPrx proxy = communicator.stringToProxy(RouteControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}
