package inside_payment.tars.insidepayment.impl;

import com.qq.tars.spring.annotation.TarsServant;
import inside_payment.tars.insidepayment.*;
import inside_payment.tars.insidepayment.service.InsidePaymentTarsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 11:38
 * @Description:
 */
@TarsServant("InsidePaymentObj")
public class InsidePaymentControllerServantImpl implements InsidePaymentControllerServant {
    @Autowired
    public InsidePaymentTarsService service;

    @Override
    public String home() {
        return "Welcome to [ InsidePayment Service ] !";
    }

    @Override
    public ResponsePaymentInfo pay(PaymentInfoTars info) {
        System.out.println("[Inside Payment Service][Pay] Pay for:" + info.getOrderId());
        return service.pay(info);
    }

    @Override
    public ResponseAccountInfo createAccount(AccountInfoTars info) {
        return service.createAccount(info);
    }

    @Override
    public ResponsePaymentInfo addMoney(String userId, String money) {
        return service.addMoney(userId, money);
    }

    @Override
    public ResponseInsidePaymentList queryPayment() {
        return service.queryPayment();
    }

    @Override
    public ResponseBalanceList queryAccount() {
        return service.queryAccount();
    }

    @Override
    public ResponsePaymentInfo drawBack(String userId, String money) {
        return service.drawBack(userId, money);
    }

    @Override
    public ResponsePaymentInfo payDifference(PaymentInfoTars info) {
        return service.payDifference(info);
    }

    @Override
    public ResponsePaymentInfo queryAddMoney() {
        return service.queryAddMoney();
    }
}
