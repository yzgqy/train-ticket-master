package inside_payment.tars.insidepayment.service;

import inside_payment.entity.Payment;
import inside_payment.tars.insidepayment.*;
import org.springframework.stereotype.Service;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 11:39
 * @Description:
 */
@Service
public class InsidePaymentTarsServiceImpl implements InsidePaymentTarsService {
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

    @Override
    public void initPayment(Payment payment) {

    }
}
