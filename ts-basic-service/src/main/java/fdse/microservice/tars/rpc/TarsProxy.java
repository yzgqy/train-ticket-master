package fdse.microservice.tars.rpc;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import fdse.microservice.tars.rpc.priceconfig.PriceControllerPrx;
import fdse.microservice.tars.rpc.route.RouteControllerPrx;
import fdse.microservice.tars.rpc.station.StationControllerPrx;
import fdse.microservice.tars.rpc.train.TrainControllerPrx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 12:02
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

    @Bean("StationControllerPrx")
    public StationControllerPrx getStationPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        StationControllerPrx proxy = communicator.stringToProxy(StationControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

    @Bean("TrainControllerPrx")
    public TrainControllerPrx getTrainPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        TrainControllerPrx proxy = communicator.stringToProxy(TrainControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

    @Bean("PriceControllerPrx")
    public PriceControllerPrx getPricePrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        PriceControllerPrx proxy = communicator.stringToProxy(PriceControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }
}
