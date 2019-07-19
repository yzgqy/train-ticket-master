package ticketinfo.tars.ticketinfo.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import ticketinfo.entity.Travel;
import ticketinfo.tars.rpc.basic.TravelResultTars;
import ticketinfo.tars.rpc.basic.TravelTars;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 14:41
 * @Description:
 */
public interface TicketInfoTarsService {
    Response<TravelResultTars> queryForTravel(TravelTars info);
    Response<String> queryForStationId(String name);
}
