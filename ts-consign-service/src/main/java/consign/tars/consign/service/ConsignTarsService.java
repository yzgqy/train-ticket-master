package consign.tars.consign.service;

import consign.tars.consign.ConsignTars;
import consign.tars.consign.ResponseConsign;
import consign.tars.consign.ResponseConsignList;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 10:09
 * @Description:
 */
public interface ConsignTarsService {
    ResponseConsign insertConsignRecord(ConsignTars consignRequest);

    ResponseConsign updateConsignRecord(ConsignTars consignRequest);

    ResponseConsignList queryByAccountId(String accountId);

    ResponseConsign queryByOrderId(String orderId);

    ResponseConsignList queryByConsignee(String consignee);
}
