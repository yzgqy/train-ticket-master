package com.trainticket.tars.payment.impl;

import com.qq.tars.spring.annotation.TarsServant;
import com.trainticket.tars.payment.*;
import com.trainticket.tars.payment.service.PaymentTarsService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 18:31
 * @Description:
 */
@TarsServant("PaymentObj")
public class PaymentControllerServantImpl  implements PaymentControllerServant {
    @Autowired
    PaymentTarsService paymentTarsService;

    @Override
    public String home() {
        return "Welcome to [ Payment Service ] !";
    }

    @Override
    public ResponsePayment pay(PaymentTars info) {
        return paymentTarsService.pay(info);
    }

    @Override
    public ResponseMoney addMoney(PaymentTars info) {
        return paymentTarsService.addMoney(info);
    }

    @Override
    public ResponsePayments query() {
        return paymentTarsService.query();
    }
}
