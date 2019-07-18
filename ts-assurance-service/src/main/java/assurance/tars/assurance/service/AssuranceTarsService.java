package assurance.tars.assurance.service;

import assurance.tars.assurance.ResponseAssurance;
import assurance.tars.assurance.ResponseAssuranceTypeBeans;
import assurance.tars.assurance.ResponsePlainAssurances;
import org.springframework.stereotype.Service;

/**
 * @Auther: yaya
 * @Date: 2019/7/10 17:46
 * @Description:
 */
public interface AssuranceTarsService {
    ResponseAssurance findAssuranceById(String id);

    ResponseAssurance findAssuranceByOrderId(String orderId);

    ResponseAssurance create(int typeIndex,String orderId);

    ResponseAssurance deleteById(String assuranceId);

    ResponseAssurance deleteByOrderId(String orderId);

    ResponseAssurance modify(String assuranceId, String orderId, int typeIndex);

    ResponsePlainAssurances getAllAssurances();

    ResponseAssuranceTypeBeans getAllAssuranceTypes();
}
