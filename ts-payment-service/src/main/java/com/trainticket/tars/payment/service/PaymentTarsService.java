package com.trainticket.tars.payment.service;

import com.trainticket.tars.payment.PaymentTars;
import com.trainticket.tars.payment.ResponseMoney;
import com.trainticket.tars.payment.ResponsePayment;
import com.trainticket.tars.payment.ResponsePayments;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 18:30
 * @Description:
 */
public interface PaymentTarsService {
    ResponsePayment pay(PaymentTars info);

    ResponseMoney addMoney(PaymentTars info);

    ResponsePayments query();

    void initPayment(PaymentTars payment);
}
