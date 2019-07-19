package security.tars.security.impl;

import com.qq.tars.common.support.Holder;
import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import security.tars.security.Response;
import security.tars.security.SecurityConfigTars;
import security.tars.security.SecurityControllerServant;
import security.tars.security.service.SecurityTarsService;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 10:45
 * @Description:
 */
@TarsServant("SecurityTars")
public class SecurityControllerServantImpl implements SecurityControllerServant {
    @Autowired
    private SecurityTarsService securityTarsService;

    @Override
    public String home() {
        return "welcome to [Security Service]";
    }

    @Override
    public Response findAllSecurityConfig(Holder<List<SecurityConfigTars>> outData) {
        System.out.println("[Security Service][Find All]");
        edu.fudan.common.util.Response<List<SecurityConfigTars>> rp = securityTarsService.findAllSecurityConfig();
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response create(SecurityConfigTars info, Holder<SecurityConfigTars> outData) {
        System.out.println("[Security Service][Create] Name:" + info.getName());
        edu.fudan.common.util.Response<SecurityConfigTars> rp =securityTarsService.addNewSecurityConfig(info);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response update(SecurityConfigTars info, Holder<SecurityConfigTars> outData) {
        System.out.println("[Security Service][Update] Name:" + info.getName());
       edu.fudan.common.util.Response<SecurityConfigTars> rp =securityTarsService.modifySecurityConfig(info);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response delete(String id, Holder<String> outData) {
        System.out.println("[Security Service][Delete] Id:" + id);
        edu.fudan.common.util.Response<String> rp =securityTarsService.deleteSecurityConfig(id);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }

    @Override
    public Response check(String accountId, Holder<String> outData) {
        System.out.println("[Security Service][Check Security] Check Account Id:" + accountId);
        edu.fudan.common.util.Response<String> rp =securityTarsService.check(accountId);
        outData.setValue(rp.getData());
        Response response = new Response();
        response.setStatus(rp.getStatus());
        response.setMsg(rp.getMsg());
        return response;
    }
}
