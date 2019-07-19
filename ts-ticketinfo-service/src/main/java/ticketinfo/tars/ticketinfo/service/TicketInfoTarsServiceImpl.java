package ticketinfo.tars.ticketinfo.service;

import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketinfo.tars.rpc.basic.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 14:42
 * @Description:
 */
@Service
public class TicketInfoTarsServiceImpl implements TicketInfoTarsService {
    @Autowired
    private BasicControllerPrx basicControllerPrx;
    @Override
    public Response<TravelResultTars> queryForTravel(TravelTars info) {
        ResponseTravelResult rp   = basicControllerPrx.queryForTravel(info);
        return new Response<>(rp.getStatus(),rp.getMsg(),rp.getData());
    }

    @Override
    public Response<String> queryForStationId(String name) {
        ResponseString rp   = basicControllerPrx.queryForStationId(name);
        return new Response<>(rp.getStatus(),rp.getMsg(),rp.getData());

    }
}
