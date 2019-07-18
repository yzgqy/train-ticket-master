package inside_payment.tars.insidepayment.service;

import inside_payment.entity.AccountInfo;
import inside_payment.entity.Payment;
import inside_payment.entity.PaymentInfo;
import inside_payment.tars.insidepayment.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 11:39
 * @Description:
 */
public interface InsidePaymentTarsService {
    ResponsePaymentInfo pay(PaymentInfoTars info );

    ResponseAccountInfo createAccount(AccountInfoTars info);

    ResponsePaymentInfo addMoney(String userId,String money);

    ResponsePaymentInfoList queryPayment();

    ResponseBalanceList queryAccount();

    ResponsePaymentInfo drawBack(String userId, String money);

    ResponsePaymentInfo payDifference(PaymentInfoTars info);

    ResponsePaymentInfo queryAddMoney();

    void initPayment(Payment payment);
}
