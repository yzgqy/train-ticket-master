package consign.tars.consign.service;

import consign.entity.Consign;
import consign.entity.ConsignRecord;
import consign.repository.ConsignRepository;
import consign.tars.consign.ConsignTars;
import consign.tars.consign.ResponseConsign;
import consign.tars.consign.ResponseConsignList;
import consign.tars.rpc.consignprice.ConsignPriceControllerPrx;
import consign.tars.rpc.consignprice.ResponseDouble;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 10:09
 * @Description:
 */
@Service
@Slf4j
public class ConsignTarsServiceImpl implements ConsignTarsService{
    @Autowired
    ConsignRepository repository;

    @Autowired
    private ConsignPriceControllerPrx consignPriceControllerPrx;

    @Override
    public ResponseConsign insertConsignRecord(ConsignTars consignRequest) {
        System.out.println("[Consign servie] [ Insert new consign record]" + consignRequest.getOrderId());

        ConsignRecord consignRecord = new ConsignRecord();
        //设置record属性
        consignRecord.setId(UUID.randomUUID());
        log.info("Order ID is :" + consignRequest.getOrderId());
        consignRecord.setOrderId(UUID.fromString(consignRequest.getOrderId()));
        consignRecord.setAccountId(UUID.fromString(consignRequest.getAccountId()));
        System.out.printf("The handle date is %s", consignRequest.getHandleDate());
        System.out.printf("The target date is %s", consignRequest.getTargetDate());
        consignRecord.setHandleDate(consignRequest.getHandleDate());
        consignRecord.setTargetDate(consignRequest.getTargetDate());
        consignRecord.setFrom(consignRequest.getFrom());
        consignRecord.setTo(consignRequest.getTo());
        consignRecord.setConsignee(consignRequest.getConsignee());
        consignRecord.setPhone(consignRequest.getPhone());
        consignRecord.setWeight(consignRequest.getWeight());

        //获得价格
        ResponseDouble re = consignPriceControllerPrx.getPriceByWeightAndRegion(String.valueOf(consignRequest.getWeight()),String.valueOf(consignRequest.isWithin()));
        consignRecord.setPrice(re.getData());

        log.info("SAVE consign info : " + consignRecord.toString());
        ConsignRecord result = repository.save(consignRecord);
        log.info("SAVE consign result : " + result.toString());
        if (result != null) {
            return new ResponseConsign(1, "You have consigned successfully! The price is " + result.getPrice(),consignRequest);
        } else {
            return new ResponseConsign(0, "Consign failed! Please try again later!", consignRequest);
        }
    }

    @Override
    public ResponseConsign updateConsignRecord(ConsignTars consignRequest) {
        System.out.println("[Consign servie] [ Update consign record]");

        ConsignRecord originalRecord = repository.findById(UUID.fromString(consignRequest.getId()));
        if (originalRecord == null) {
            return this.insertConsignRecord(consignRequest);
            // return new Response<>(0, "Update failed, There is no Consign Record", consignRequest);
        }
        originalRecord.setAccountId(UUID.fromString(consignRequest.getAccountId()));
        originalRecord.setHandleDate(consignRequest.getHandleDate());
        originalRecord.setTargetDate(consignRequest.getTargetDate());
        originalRecord.setFrom(consignRequest.getFrom());
        originalRecord.setTo(consignRequest.getTo());
        originalRecord.setConsignee(consignRequest.getConsignee());
        originalRecord.setPhone(consignRequest.getPhone());
        //重新计算价格
        if (originalRecord.getWeight() != consignRequest.getWeight()) {
            ResponseDouble re = consignPriceControllerPrx.getPriceByWeightAndRegion(String.valueOf(consignRequest.getWeight()),String.valueOf(consignRequest.isWithin()));
            originalRecord.setPrice(re.getData());
        } else {
            originalRecord.setPrice(originalRecord.getPrice());
        }
        originalRecord.setConsignee(consignRequest.getConsignee());
        originalRecord.setPhone(consignRequest.getPhone());
        originalRecord.setWeight(consignRequest.getWeight());
        repository.save(originalRecord);
        return new ResponseConsign(1, "Update consign success", originalRecord.toTars());
    }

    @Override
    public ResponseConsignList queryByAccountId(String accountId) {
        List<ConsignRecord> consignRecords = repository.findByAccountId(UUID.fromString(accountId));
        List<ConsignTars> list = new ArrayList<>();
        if (consignRecords != null && consignRecords.size() > 0) {
            for (ConsignRecord consignRecord : consignRecords) {
                list.add(consignRecord.toTars());
            }
            return new ResponseConsignList(1, "Find consign by account id success", list);
        } else
            return new ResponseConsignList(0, "No Content according to accountId", list);

    }

    @Override
    public ResponseConsign queryByOrderId(String orderId) {
        ConsignRecord consignRecords = repository.findByOrderId(UUID.fromString(orderId));
        if (consignRecords != null )
            return new ResponseConsign(1, "Find consign by order id success", consignRecords.toTars());
        else
            return new ResponseConsign(0, "No Content according to order id", null);

    }

    @Override
    public ResponseConsignList queryByConsignee(String consignee) {
        List<ConsignRecord> consignRecords = repository.findByConsignee(consignee);
        List<ConsignTars> list = new ArrayList<>();
        if (consignRecords != null && consignRecords.size() > 0) {
            for (ConsignRecord consignRecord : consignRecords) {
                list.add(consignRecord.toTars());
            }
            return new ResponseConsignList(1, "Find consign by consignee success", list);
        }else
            return new ResponseConsignList(0, "No Content according to consignee", list);
    }
}
