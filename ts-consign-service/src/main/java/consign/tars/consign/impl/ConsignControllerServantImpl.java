package consign.tars.consign.impl;

import com.qq.tars.spring.annotation.TarsServant;
import consign.tars.consign.*;
import consign.tars.consign.service.ConsignTarsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 10:07
 * @Description:
 */
@TarsServant("ConsignObj")
public class ConsignControllerServantImpl implements ConsignControllerServant {
    @Autowired
    ConsignTarsService consignTarsService;

    @Override
    public String home() {
        return "Welcome to [ Consign Service ] !";
    }

    @Override
    public ResponseConsign insertConsign(ConsignTars request) {
        return consignTarsService.insertConsignRecord(request);
    }

    @Override
    public ResponseConsign updateConsign(ConsignTars request) {
        return consignTarsService.updateConsignRecord(request);
    }

    @Override
    public ResponseConsignList findByAccountId(String id) {
        return consignTarsService.queryByAccountId(id);
    }

    @Override
    public ResponseConsign findByOrderId(String id) {
        return consignTarsService.queryByOrderId(id);
    }

    @Override
    public ResponseConsignList findByConsignee(String consignee) {
        return consignTarsService.queryByConsignee(consignee);
    }
}
