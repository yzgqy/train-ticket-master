package security.tars.security.service;

import com.qq.tars.common.support.Holder;
import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.entity.SecurityConfig;
import security.repository.SecurityRepository;
import security.tars.rpc.order.OrderControllerPrx;
import security.tars.rpc.order.OrderSecurityTars;
import security.tars.rpc.orderother.OrderOtherControllerPrx;
import security.tars.rpc.orderother.ResponseOrderSecurity;
import security.tars.security.SecurityConfigTars;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 10:45
 * @Description:
 */
@Service
public class SecurityTarsServiceImpl implements SecurityTarsService {

    @Autowired
    private SecurityRepository securityRepository;
    @Autowired
    private OrderControllerPrx orderControllerPrx;
    @Autowired
    private OrderOtherControllerPrx orderOtherControllerPrx;


    @Override
    public Response<List<SecurityConfigTars>> findAllSecurityConfig() {
        ArrayList<SecurityConfig> securityConfigs = securityRepository.findAll();
        List<SecurityConfigTars> securityConfigTarsList = new ArrayList<>();
        if (securityConfigs != null && securityConfigs.size() > 0) {
            for(SecurityConfig securityConfig:securityConfigs)
                securityConfigTarsList.add(securityConfig.toTars());
            return new Response<>(1, "Success", securityConfigTarsList);
        }
        return new Response<>(0, "No Content", null);

    }

    @Override
    public Response<SecurityConfigTars> addNewSecurityConfig(SecurityConfigTars info) {
        SecurityConfig sc = securityRepository.findByName(info.getName());
        if (sc != null) {
            return new Response<>(0, "Security Config Already Exist", null);
        } else {
            SecurityConfig config = new SecurityConfig();
            config.setId(UUID.randomUUID());
            config.setName(info.getName());
            config.setValue(info.getValue());
            config.setDescription(info.getDescription());
            securityRepository.save(config);
            return new Response<>(1, "Success", config.toTars());
        }
    }

    @Override
    public Response<SecurityConfigTars> modifySecurityConfig(SecurityConfigTars info) {
        SecurityConfig sc = securityRepository.findById(UUID.fromString(info.getId()));
        if (sc == null) {
            return new Response<>(0, "Security Config Not Exist", null);
        } else {
            sc.setName(info.getName());
            sc.setValue(info.getValue());
            sc.setDescription(info.getDescription());
            securityRepository.save(sc);
            return new Response<>(1, "Success", sc.toTars());
        }
    }

    @Override
    public Response<String> deleteSecurityConfig(String id) {
        securityRepository.deleteById(UUID.fromString(id));
        SecurityConfig sc = securityRepository.findById(UUID.fromString(id));
        if (sc == null) {
            return new Response<>(1, "Success", id);
        } else {
            return new Response<>(0, "Reason Not clear", id);
        }
    }

    @Override
    public Response<String> check(String accountId) {
        //1.获取自己过去一小时的订单数和总有效票数
        System.out.println("[Security Service][Get Order Num Info]");
        OrderSecurityTars orderResult = getSecurityOrderInfoFromOrder(new Date().getTime(), accountId);
        security.tars.rpc.orderother.OrderSecurityTars orderOtherResult = getSecurityOrderOtherInfoFromOrder(new Date().getTime(), accountId);
        int orderInOneHour = orderOtherResult.getOrderNumInLastOneHour() + orderResult.getOrderNumInLastOneHour();
        int totalValidOrder = orderOtherResult.getOrderNumOfValidOrder() + orderOtherResult.getOrderNumOfValidOrder();
        //2.获取关键配置信息
        System.out.println("[Security Service][Get Security Config Info]");
        SecurityConfig configMaxInHour = securityRepository.findByName("max_order_1_hour");
        SecurityConfig configMaxNotUse = securityRepository.findByName("max_order_not_use");
        System.out.println("[Security Service] Max In One Hour:" + configMaxInHour.getValue() + " Max Not Use:" + configMaxNotUse.getValue());
        int oneHourLine = Integer.parseInt(configMaxInHour.getValue());
        int totalValidLine = Integer.parseInt(configMaxNotUse.getValue());
        if (orderInOneHour > oneHourLine || totalValidOrder > totalValidLine) {
            return new Response<>(0, "Too much order in last one hour or too much valid order", accountId);
        } else {
            return new Response<>(1, "Success.r", accountId);
        }
    }

    private OrderSecurityTars getSecurityOrderInfoFromOrder(long checkDate, String accountId) {
        System.out.println("[Security Service][Get Order Info For Security] Getting....");
        Holder<OrderSecurityTars> orderSecurityTarsOut = new Holder<>();
        security.tars.rpc.order.Response rp = orderControllerPrx.securityInfoCheck(checkDate,accountId,orderSecurityTarsOut);
        OrderSecurityTars result =  orderSecurityTarsOut.getValue();
        System.out.println("[Security Service][Get Order Info For Security] Last One Hour:" + result.getOrderNumInLastOneHour()
                + " Total Valid Order:" + result.getOrderNumOfValidOrder());
        return result;
    }

    private security.tars.rpc.orderother.OrderSecurityTars getSecurityOrderOtherInfoFromOrder(long checkDate, String accountId) {
        System.out.println("[Security Service][Get Order Other Info For Security] Getting....");
        ResponseOrderSecurity responseOrderSecurity = orderOtherControllerPrx.securityInfoCheck(checkDate,accountId);

        security.tars.rpc.orderother.OrderSecurityTars result =  responseOrderSecurity.getData();
//        OrderSecurity result = restTemplate.postForObject(
//                "http://ts-order-other-service:12032/getOrderOtherInfoForSecurity",info,
//                OrderSecurity.class);
        System.out.println("[Security Service][Get Order Other Info For Security] Last One Hour:" + result.getOrderNumInLastOneHour()
                + " Total Valid Order:" + result.getOrderNumOfValidOrder());
        return result;
    }
}
