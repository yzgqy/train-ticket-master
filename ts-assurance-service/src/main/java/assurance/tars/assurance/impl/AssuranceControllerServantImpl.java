package assurance.tars.assurance.impl;

import assurance.tars.assurance.AssuranceControllerServant;
import assurance.tars.assurance.ResponseAssurance;
import assurance.tars.assurance.ResponseAssuranceTypeBeans;
import assurance.tars.assurance.ResponsePlainAssurances;
import assurance.tars.assurance.service.AssuranceTarsService;
import com.qq.tars.spring.annotation.TarsServant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/10 17:45
 * @Description:
 */
@TarsServant("AssuranceObj")
public class AssuranceControllerServantImpl implements AssuranceControllerServant {
    @Autowired
    private AssuranceTarsService assuranceTarsService;

    @Override
    public String home() {
        return "Welcome to [ Assurance Service ] !";
    }

    @Override
    public ResponsePlainAssurances getAllAssurances() {

        System.out.println("[Assurances Service][Get All Assurances]");
        return assuranceTarsService.getAllAssurances();
    }

    @Override
    public ResponseAssuranceTypeBeans getAllAssuranceType() {
        System.out.println("[Assurances Service][Get Assurance Type]");
        return assuranceTarsService.getAllAssuranceTypes();
    }

    @Override
    public ResponseAssurance deleteAssurance(String assuranceId) {
        System.out.println("[Assurances Service][Delete Assurance]");
        return assuranceTarsService.deleteById(assuranceId);
    }

    @Override
    public ResponseAssurance deleteAssuranceByOrderId(String orderId) {
        System.out.println("[Assurances Service][Delete Assurance by orderId]");
        return assuranceTarsService.deleteByOrderId(orderId);

    }

    @Override
    public ResponseAssurance modifyAssurance(String assuranceId, String orderId, int typeIndex) {

        System.out.println("[Assurances Service][Modify Assurance]");
        return assuranceTarsService.modify(assuranceId, orderId, typeIndex);
    }

    @Override
    public ResponseAssurance createNewAssurance(int typeIndex, String orderId) {

        return assuranceTarsService.create(typeIndex, orderId);

    }

    @Override
    public ResponseAssurance getAssuranceById(String assuranceId) {

        return assuranceTarsService.findAssuranceById(assuranceId);

    }

    @Override
    public ResponseAssurance findAssuranceByOrderId(String orderId) {

        return assuranceTarsService.findAssuranceByOrderId(orderId);

    }
}
