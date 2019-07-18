package other.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import other.tars.rpc.station.StationControllerPrx;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 16:05
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("StationPrx")
    public StationControllerPrx getStationPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        StationControllerPrx proxy = communicator.stringToProxy(StationControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}
