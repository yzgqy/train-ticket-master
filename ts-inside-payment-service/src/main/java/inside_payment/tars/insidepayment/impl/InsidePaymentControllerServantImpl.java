package inside_payment.tars.insidepayment.impl;

import com.qq.tars.spring.annotation.TarsServant;
import inside_payment.tars.insidepayment.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 11:38
 * @Description:
 */
@TarsServant("InsidePaymentObj")
public class InsidePaymentControllerServantImpl implements InsidePaymentControllerServant {
    @Override
    public String home() {
        return null;
    }

    @Override
    public ResponsePaymentInfo pay(PaymentInfoTars info) {
        return null;
    }

    @Override
    public ResponseAccountInfo createAccount(AccountInfoTars info) {
        return null;
    }

    @Override
    public ResponsePaymentInfo addMoney(String userId, String money) {
        return null;
    }

    @Override
    public ResponsePaymentInfoList queryPayment() {
        return null;
    }

    @Override
    public ResponseBalanceList queryAccount() {
        return null;
    }

    @Override
    public ResponsePaymentInfo drawBack(String userId, String money) {
        return null;
    }

    @Override
    public ResponsePaymentInfo payDifference(PaymentInfoTars info) {
        return null;
    }

    @Override
    public ResponsePaymentInfo queryAddMoney() {
        return null;
    }
}
