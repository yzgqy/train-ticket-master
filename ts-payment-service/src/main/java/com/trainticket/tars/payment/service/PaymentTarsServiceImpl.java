package com.trainticket.tars.payment.service;

import com.trainticket.entity.Money;
import com.trainticket.entity.Payment;
import com.trainticket.repository.AddMoneyRepository;
import com.trainticket.repository.PaymentRepository;
import com.trainticket.tars.payment.PaymentTars;
import com.trainticket.tars.payment.ResponseMoney;
import com.trainticket.tars.payment.ResponsePayment;
import com.trainticket.tars.payment.ResponsePayments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/11 18:30
 * @Description:
 */
@Service
public class PaymentTarsServiceImpl implements PaymentTarsService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    AddMoneyRepository addMoneyRepository;

    @Override
    public ResponsePayment pay(PaymentTars info) {
        if(paymentRepository.findByOrderId(info.getOrderId()) == null){
            Payment payment = new Payment();
            payment.setOrderId(info.getOrderId());
            payment.setPrice(info.getPrice());
            payment.setUserId(info.getUserId());
            paymentRepository.save(payment);
            return new ResponsePayment(1, "Pay Success", null);
        }else{
            return new ResponsePayment(0, "Pay Failed, order not found with order id" +info.getOrderId(), null);
        }
    }

    @Override
    public ResponseMoney addMoney(PaymentTars info) {
        Money addMoney = new Money();
        addMoney.setUserId(info.getUserId());
        addMoney.setMoney(info.getPrice());
        addMoneyRepository.save(addMoney);
        return new ResponseMoney(1,"Add Money Success", addMoney.toTars());
    }

    @Override
    public ResponsePayments query() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentTars> paymentTarsList = new ArrayList<>();

        if(payments!= null && payments.size() >0){
            for(Payment payment:payments){
                paymentTarsList.add(payment.toTars());
            }
            return new ResponsePayments(1,"Query Success",  paymentTarsList);
        }else {
            return new ResponsePayments(0, "No Content", null);
        }
    }

    @Override
    public void initPayment(PaymentTars payment) {
        Payment paymentTemp = paymentRepository.findById(payment.getId());
        if(paymentTemp == null){
            paymentRepository.save(new Payment(payment));
        }else{
            System.out.println("[Payment Service][Init Payment] Already Exists:" + payment.getId());
        }

    }
}
