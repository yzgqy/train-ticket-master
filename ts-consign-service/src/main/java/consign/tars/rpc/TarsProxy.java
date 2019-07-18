package consign.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import consign.tars.rpc.consignprice.ConsignPriceControllerPrx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 16:58
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("ConsignPricePrx")
    public ConsignPriceControllerPrx getVetPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        ConsignPriceControllerPrx proxy = communicator.stringToProxy(ConsignPriceControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}
