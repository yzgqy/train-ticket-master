package adminbasic.tars.rpc;

import adminbasic.tars.rpc.config.ConfigControllerPrx;
import adminbasic.tars.rpc.contacts.ContactsControllerPrx;
import adminbasic.tars.rpc.priceconfig.PriceControllerPrx;
import adminbasic.tars.rpc.station.StationControllerPrx;
import adminbasic.tars.rpc.train.TrainControllerPrx;
import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 15:31
 * @Description:
 */
@Configuration
public class TarsProxy {
    @Bean("ConfigPrx")
    public ConfigControllerPrx getConfigPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        ConfigControllerPrx proxy = communicator.stringToProxy(ConfigControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

    @Bean("ContactsPrx")
    public ContactsControllerPrx getContactsPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        ContactsControllerPrx proxy = communicator.stringToProxy(ContactsControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }


    @Bean("PricePrx")
    public PriceControllerPrx getPricePrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        PriceControllerPrx proxy = communicator.stringToProxy(PriceControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }


    @Bean("StationPrx")
    public StationControllerPrx getStationPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        StationControllerPrx proxy = communicator.stringToProxy(StationControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }


    @Bean("TrainPrx")
    public TrainControllerPrx getTrainPrx(){
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        TrainControllerPrx proxy = communicator.stringToProxy(TrainControllerPrx.class, "TrainTicketApp.ConsignPriceServer.ConsignPriceObj");
        return proxy;
    }

}