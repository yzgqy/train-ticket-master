package assurance.tars.assurance.service;

import assurance.entity.Assurance;
import assurance.entity.AssuranceType;
import assurance.entity.AssuranceTypeBean;
import assurance.entity.PlainAssurance;
import assurance.repository.AssuranceRepository;
import assurance.tars.assurance.*;
import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/10 17:51
 * @Description:
 */
@Service
public class AssuranceTarsServiceImpl implements AssuranceTarsService{

    @Autowired
    private AssuranceRepository assuranceRepository;

    @Override
    public ResponseAssurance findAssuranceById(String id) {
        Assurance assurance = assuranceRepository.findById(UUID.fromString(id));
        if (assurance == null) {
            return new ResponseAssurance(0, "No Conotent by this id", null);
        } else {
            return new ResponseAssurance(1, "Find Assurace Success", assurance.toTars());
        }
    }

    @Override
    public ResponseAssurance findAssuranceByOrderId(String orderId) {
        Assurance assurance = assuranceRepository.findByOrderId(UUID.fromString(orderId));
        if (assurance == null) {
            return new ResponseAssurance(0, "No Content by this orderId", null);
        } else {
            return new ResponseAssurance(1, "Find Assurace Success", assurance.toTars());
        }
    }

    @Override
    public ResponseAssurance create(int typeIndex, String orderId) {
        Assurance a = assuranceRepository.findByOrderId(UUID.fromString(orderId));
        // AddAssuranceResult aar = new AddAssuranceResult();
        AssuranceType at = AssuranceType.getTypeByIndex(typeIndex);
        if (a != null) {
            System.out.println("[Assurance-Add&Delete-Service][AddAssurance] Fail.Assurance already exists");
            return new ResponseAssurance(0, "Fail.Assurance already exists", null);
        } else if (at == null) {
            System.out.println("[Assurance-Add&Delete-Service][AddAssurance] Fail.Assurance type doesn't exist");
            return new ResponseAssurance(0, "Fail.Assurance type doesn't exist", null);
        } else {
            Assurance assurance = new Assurance(UUID.randomUUID(), UUID.fromString(orderId), at);
            assuranceRepository.save(assurance);
            System.out.println("[Assurance-Add&Delete-Service][AddAssurance] Success.");
            return new ResponseAssurance(1, "Success", assurance.toTars());
        }
    }

    @Override
    public ResponseAssurance deleteById(String assuranceId) {
        UUID id = UUID.fromString(assuranceId);
        assuranceRepository.deleteById(id);
        Assurance a = assuranceRepository.findById(id);
//        DeleteAssuranceResult dar = new DeleteAssuranceResult();
        if (a == null) {
            System.out.println("[Assurance-Add&Delete-Service][DeleteAssurance] Success.");
            return new ResponseAssurance(1, "Delete Success with Assurance id", a.toTars());
        } else {
            System.out.println("[Assurance-Add&Delete-Service][DeleteAssurance] Fail.Assurance not clear.");
            return new ResponseAssurance(0, "Fail.Assurance not clear", null);
        }
    }

    @Override
    public ResponseAssurance deleteByOrderId(String orderId) {
        UUID id = UUID.fromString(orderId);
        assuranceRepository.removeAssuranceByOrderId(id);
        Assurance isExistAssurace = assuranceRepository.findByOrderId(id);
        // DeleteAssuranceResult dar = new DeleteAssuranceResult();
        if (isExistAssurace == null) {
            System.out.println("[Assurance-Add&Delete-Service][DeleteAssurance] Success.");
            return new ResponseAssurance(1, "Delete Success with Order Id", isExistAssurace.toTars());
        } else {
            System.out.println("[Assurance-Add&Delete-Service][DeleteAssurance] Fail.Assurance not clear.");
            return new ResponseAssurance(0, "Fail.Assurance not clear", null);
        }
    }

    @Override
    public ResponseAssurance modify(String assuranceId, String orderId, int typeIndex) {
        ResponseAssurance oldAssuranceResponse = findAssuranceById(assuranceId);
        // ModifyAssuranceResult mcr = new ModifyAssuranceResult();
        Assurance oldAssurance = new Assurance(oldAssuranceResponse.getData());
        if (oldAssurance == null) {
            System.out.println("[Assurance-Modify-Service][ModifyAssurance] Fail.Assurance not found.");
            return new ResponseAssurance(0, "Fail.Assurance not found.", null);
        } else {
            AssuranceType at = AssuranceType.getTypeByIndex(typeIndex);
            if (at != null) {
                oldAssurance.setType(at);
                assuranceRepository.save(oldAssurance);
                System.out.println("[Assurance-Modify-Service][ModifyAssurance] Success.");
                return new ResponseAssurance(1, "Modify Success", oldAssurance.toTars());
            } else {
                System.out.println("[Assurance-Modify-Service][ModifyAssurance] Fail.Assurance Type not exist.");
                return new ResponseAssurance(0, "Assurance Type not exist", null);
            }
        }
    }

    @Override
    public ResponsePlainAssurances getAllAssurances() {
        List<Assurance> as = assuranceRepository.findAll();
        if (as != null && as.size() > 0) {
            ArrayList<PlainAssurance> result = new ArrayList<PlainAssurance>();
            ArrayList<PlainAssuranceTars> plainAssuranceTarsArrayList = new ArrayList<>();
            for (Assurance a : as) {
                PlainAssurance pa = new PlainAssurance();
                pa.setId(a.getId());
                pa.setOrderId(a.getOrderId());
                pa.setTypeIndex(a.getType().getIndex());
                pa.setTypeName(a.getType().getName());
                pa.setTypePrice(a.getType().getPrice());
                result.add(pa);
                plainAssuranceTarsArrayList.add(pa.toTars());
            }
            return new ResponsePlainAssurances(1, "Success", plainAssuranceTarsArrayList);
        } else {
            return new ResponsePlainAssurances(0, "No Content, Assurance is empty", null);
        }
    }

    @Override
    public ResponseAssuranceTypeBeans getAllAssuranceTypes() {

        List<AssuranceTypeBean> atlist = new ArrayList<AssuranceTypeBean>();
        List<AssuranceTypeBeanTars> tarsList = new ArrayList<>();
        for (AssuranceType at : AssuranceType.values()) {
            AssuranceTypeBean atb = new AssuranceTypeBean();
            atb.setIndex(at.getIndex());
            atb.setName(at.getName());
            atb.setPrice(at.getPrice());
            atlist.add(atb);
            tarsList.add(atb.toTars());
        }
        if (atlist.size() > 0) {
            return new ResponseAssuranceTypeBeans(1, "Find All Assurance", tarsList);
        } else {
            return new ResponseAssuranceTypeBeans(0, "Assurance is Empty", null);
        }
    }
}
