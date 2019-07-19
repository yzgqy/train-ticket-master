package ticketinfo.tars.ticketinfo.impl;

import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import ticketinfo.tars.rpc.basic.TravelResultTars;
import ticketinfo.tars.rpc.basic.TravelTars;
import ticketinfo.tars.ticketinfo.Response;
import ticketinfo.tars.ticketinfo.TicketInfoControllerServant;
import ticketinfo.tars.ticketinfo.service.TicketInfoTarsService;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 14:39
 * @Description:
 */
@TarsServant("TicketInfoObj")
public class TicketInfoControllerServantImpl implements TicketInfoControllerServant {
    @Autowired
    private TicketInfoTarsService ticketInfoTarsService;

    @Override
    public String home() {
        return "Welcome to [ TicketInfo Service ] !";
    }

    @Override
    public Response queryForTravel(TravelTars info, Holder<TravelResultTars> outData) {
        edu.fudan.common.util.Response<TravelResultTars> rp = ticketInfoTarsService.queryForTravel(info);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response queryForStationId(String name, Holder<String> outData) {
        edu.fudan.common.util.Response<String> rp = ticketInfoTarsService.queryForStationId(name);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }
}
