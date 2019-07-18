package execute.tars.execute.impl;

import com.qq.tars.spring.annotation.TarsServant;
import execute.tars.execute.ExecuteControlllerServant;
import execute.tars.execute.Response;
import execute.tars.execute.service.ExecuteTarsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/18 17:45
 * @Description:
 */
@TarsServant("ExecuteObj")
public class ExecuteControlllerServantImpl implements ExecuteControlllerServant {
    @Autowired
    private ExecuteTarsService executeTarsService;
    @Override
    public String home() {
        return "Welcome to [ Execute Service ] !";
    }

    @Override
    public Response executeTicket(String orderId) {
        System.out.println("[Execute Service][Execute] Id:" + orderId);
        // null
        edu.fudan.common.util.Response rp = executeTarsService.ticketExecute(orderId);
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response collectTicket(String orderId) {
        System.out.println("[Execute Service][Collect] Id:" + orderId);
        // null
        edu.fudan.common.util.Response rp =executeTarsService.ticketCollect(orderId);
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }
}
