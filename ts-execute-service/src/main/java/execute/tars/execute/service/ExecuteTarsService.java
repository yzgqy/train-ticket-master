package execute.tars.execute.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 17:47
 * @Description:
 */
public interface ExecuteTarsService {
    Response ticketExecute(String orderId);

    Response ticketCollect(String orderId);

}
