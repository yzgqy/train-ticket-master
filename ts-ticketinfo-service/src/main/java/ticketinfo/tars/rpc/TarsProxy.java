package ticketinfo.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ticketinfo.tars.rpc.basic.BasicControllerPrx;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 14:38
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("BasicPrx")
    public BasicControllerPrx getBasicPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        BasicControllerPrx proxy = communicator.stringToProxy(BasicControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}
