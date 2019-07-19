package inside_payment.tars.insidepayment.service;

import com.qq.tars.common.support.Holder;
import inside_payment.entity.*;
import inside_payment.repository.AddMoneyRepository;
import inside_payment.repository.PaymentRepository;
import inside_payment.tars.insidepayment.*;
import inside_payment.tars.rpc.order.OrderControllerPrx;
import inside_payment.tars.rpc.order.OrderTars;
import inside_payment.tars.rpc.order.Response;
import inside_payment.tars.rpc.orderother.OrderOtherControllerPrx;
import inside_payment.tars.rpc.orderother.ResponseOrder;
import inside_payment.tars.rpc.payment.PaymentControllerPrx;
import inside_payment.tars.rpc.payment.PaymentTars;
import inside_payment.tars.rpc.payment.ResponsePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 11:39
 * @Description:
 */
@Service
public class InsidePaymentTarsServiceImpl implements InsidePaymentTarsService {
    @Autowired
    public AddMoneyRepository addMoneyRepository;

    @Autowired
    public PaymentRepository paymentRepository;

    @Autowired
    public OrderControllerPrx orderControllerPrx;

    @Autowired
    public OrderOtherControllerPrx orderOtherControllerPrx;
    @Autowired
    public PaymentControllerPrx paymentControllerPrx;

    @Override
    public ResponsePaymentInfo pay(PaymentInfoTars info) {
        String userId = info.getUserId();
        edu.fudan.common.util.Response<OrderTars> result = new edu.fudan.common.util.Response<>();
        if (info.getTripId().startsWith("G") || info.getTripId().startsWith("D")) {
            Holder<OrderTars> orderTarsOut = new Holder<>();
            Response rp1 = orderControllerPrx.getOrderById(info.getOrderId(),orderTarsOut);
            result.setStatus(rp1.getStatus());
            result.setMsg(rp1.getMsg());
            result.setData(orderTarsOut.getValue());
        } else {
            ResponseOrder rp2 = orderOtherControllerPrx.getOrderById(info.getOrderId());
            result.setStatus(rp2.getStatus());
            result.setMsg(rp2.getMsg());
            result.setData(new OrderTars(rp2.getData()));
        }


        if (result.getStatus() == 1) {
            OrderTars order = result.getData();
            if (order.getStatus() != OrderStatus.NOTPAID.getCode()) {
                System.out.println("[Inside Payment Service][Pay] Error. Order status Not allowed to Pay.");
                return new ResponsePaymentInfo(0, "Error. Order status Not allowed to Pay.", null);
            }

            Payment payment = new Payment();
            payment.setOrderId(info.getOrderId());
            payment.setPrice(order.getPrice());
            payment.setUserId(userId);

            //判断一下账户余额够不够，不够要去站外支付
            List<Payment> payments = paymentRepository.findByUserId(userId);
            List<Money> addMonies = addMoneyRepository.findByUserId(userId);
            Iterator<Payment> paymentsIterator = payments.iterator();
            Iterator<Money> addMoniesIterator = addMonies.iterator();

            BigDecimal totalExpand = new BigDecimal("0");
            while (paymentsIterator.hasNext()) {
                Payment p = paymentsIterator.next();
                totalExpand = totalExpand.add(new BigDecimal(p.getPrice()));
            }
            totalExpand = totalExpand.add(new BigDecimal(order.getPrice()));

            BigDecimal money = new BigDecimal("0");
            while (addMoniesIterator.hasNext()) {
                Money addMoney = addMoniesIterator.next();
                money = money.add(new BigDecimal(addMoney.getMoney()));
            }

            if (totalExpand.compareTo(money) > 0) {
                //站外支付
                PaymentTars outsidePaymentInfo = new PaymentTars();
                outsidePaymentInfo.setOrderId(info.getOrderId());
                outsidePaymentInfo.setUserId(userId);
                outsidePaymentInfo.setPrice(order.getPrice());

                /****这里调用第三方支付***/

                ResponsePayment outsidePaySuccess = paymentControllerPrx.pay(outsidePaymentInfo);

//                Response outsidePaySuccess = reOutsidePaySuccess.getBody();

                System.out.println("Out pay result: " +outsidePaySuccess.toString());
                if (outsidePaySuccess.getStatus() == 1) {
                    payment.setType(PaymentType.O);
                    paymentRepository.save(payment);
                    setOrderStatus(info.getTripId(), info.getOrderId());
                    return new ResponsePaymentInfo(1, "Payment Success " +    outsidePaySuccess.getMsg(), null);
                } else {
                    return new ResponsePaymentInfo(0, "Payment Failed:  " +  outsidePaySuccess.getMsg(), null);
                }
            } else {
                setOrderStatus(info.getTripId(), info.getOrderId());
                payment.setType(PaymentType.P);
                paymentRepository.save(payment);
            }
            return new ResponsePaymentInfo(1, "Payment Success", null);

        } else {
            return new ResponsePaymentInfo(0, "Payment Failed, Order Not Exists", null);
        }
    }

    @Override
    public ResponseAccountInfo createAccount(AccountInfoTars info) {
        List<Money> list = addMoneyRepository.findByUserId(info.getUserId());
        if (list.size() == 0) {
            Money addMoney = new Money();
            addMoney.setMoney(info.getMoney());
            addMoney.setUserId(info.getUserId());
            addMoney.setType(MoneyType.A);
            addMoneyRepository.save(addMoney);
            return new ResponseAccountInfo(1, "Create Account Success", null);
        } else {
            return new ResponseAccountInfo(0, "Create Account Failed, Account already Exists", null);
        }
    }

    @Override
    public ResponsePaymentInfo addMoney(String userId, String money) {
        if (addMoneyRepository.findByUserId(userId) != null) {
            Money addMoney = new Money();
            addMoney.setUserId(userId);
            addMoney.setMoney(money);
            addMoney.setType(MoneyType.A);
            addMoneyRepository.save(addMoney);
            return new ResponsePaymentInfo(1, "Add Money Success", null);
        } else {
            return new ResponsePaymentInfo(0, "Add Money Failed", null);
        }
    }

    @Override
    public ResponseInsidePaymentList queryPayment() {
        List<Payment> payments = paymentRepository.findAll();
        List<InsidePaymentTars>insidePaymentTarsList = new ArrayList<>();
        if (payments != null && payments.size() > 0) {
            for (Payment payment : payments)
                insidePaymentTarsList.add(payment.toTars());
            return new ResponseInsidePaymentList(1, "Query Payment Success", insidePaymentTarsList);

        }else
            return new ResponseInsidePaymentList(0, "Query Payment Failed", null);

    }

    @Override
    public ResponseBalanceList queryAccount() {
        List<Balance> result = new ArrayList<Balance>();
        List<Money> list = addMoneyRepository.findAll();
        Iterator<Money> ite = list.iterator();
        HashMap<String, String> map = new HashMap<String, String>();
        while (ite.hasNext()) {
            Money addMoney = ite.next();
            if (map.containsKey(addMoney.getUserId())) {
                BigDecimal money = new BigDecimal(map.get(addMoney.getUserId()));
                map.put(addMoney.getUserId(), money.add(new BigDecimal(addMoney.getMoney())).toString());
            } else {
                map.put(addMoney.getUserId(), addMoney.getMoney());
            }
        }

        Iterator ite1 = map.entrySet().iterator();
        while (ite1.hasNext()) {
            Map.Entry entry = (Map.Entry) ite1.next();
            String userId = (String) entry.getKey();
            String money = (String) entry.getValue();

            List<Payment> payments = paymentRepository.findByUserId(userId);
            Iterator<Payment> iterator = payments.iterator();
            String totalExpand = "0";
            while (iterator.hasNext()) {
                Payment p = iterator.next();
                BigDecimal expand = new BigDecimal(totalExpand);
                totalExpand = expand.add(new BigDecimal(p.getPrice())).toString();
            }
            String balanceMoney = new BigDecimal(money).subtract(new BigDecimal(totalExpand)).toString();
            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalance(balanceMoney);
            result.add(balance);
        }

        List<BalanceTars> balanceTarsList = new ArrayList<>();
        for(Balance balance:result ){
            balanceTarsList.add(balance.toTars());
        }
        return new ResponseBalanceList(1, "Success", balanceTarsList);

    }

    @Override
    public ResponsePaymentInfo drawBack(String userId, String money) {
        if (addMoneyRepository.findByUserId(userId) != null) {
            Money addMoney = new Money();
            addMoney.setUserId(userId);
            addMoney.setMoney(money);
            addMoney.setType(MoneyType.D);
            addMoneyRepository.save(addMoney);
            return new ResponsePaymentInfo(1, "Draw Back Money Scuuess", null);
        } else {
            return new ResponsePaymentInfo(0, "Draw Back Money Failed", null);
        }
    }

    @Override
    public ResponsePaymentInfo payDifference(PaymentInfoTars info) {
        String userId = info.getUserId();

        Payment payment = new Payment();
        payment.setOrderId(info.getOrderId());
        payment.setPrice(info.getPrice());
        payment.setUserId(info.getUserId());


        List<Payment> payments = paymentRepository.findByUserId(userId);
        List<Money> addMonies = addMoneyRepository.findByUserId(userId);
        Iterator<Payment> paymentsIterator = payments.iterator();
        Iterator<Money> addMoniesIterator = addMonies.iterator();

        BigDecimal totalExpand = new BigDecimal("0");
        while (paymentsIterator.hasNext()) {
            Payment p = paymentsIterator.next();
            totalExpand.add(new BigDecimal(p.getPrice()));
        }
        totalExpand.add(new BigDecimal(info.getPrice()));

        BigDecimal money = new BigDecimal("0");
        while (addMoniesIterator.hasNext()) {
            Money addMoney = addMoniesIterator.next();
            money.add(new BigDecimal(addMoney.getMoney()));
        }

        if (totalExpand.compareTo(money) > 0) {
            //站外支付
            PaymentTars outsidePaymentInfo = new PaymentTars();
            outsidePaymentInfo.setOrderId(info.getOrderId());
            outsidePaymentInfo.setUserId(userId);
            outsidePaymentInfo.setPrice(info.getPrice());

            ResponsePayment outsidePaySuccess = paymentControllerPrx.pay(outsidePaymentInfo);
            if (outsidePaySuccess.getStatus() == 1) {
                payment.setType(PaymentType.E);
                paymentRepository.save(payment);
                return new ResponsePaymentInfo(1, "Pay Difference Success", null);
            } else {
                return new ResponsePaymentInfo(0, "Pay Difference Failed", null);
            }
        } else {
            payment.setType(PaymentType.E);
            paymentRepository.save(payment);
        }
        return new ResponsePaymentInfo(1, "Pay Difference Success", null);

    }

    @Override
    public ResponsePaymentInfo queryAddMoney() {
        List<Money> monies = addMoneyRepository.findAll();
        if (monies != null && monies.size() > 0) {
            return new ResponsePaymentInfo(1, "Query Money Success", null);
        } else {
            return new ResponsePaymentInfo(0, "", null);
        }
    }

    @Override
    public void initPayment(Payment payment) {
        Payment paymentTemp = paymentRepository.findById(payment.getId());
        if (paymentTemp == null) {
            paymentRepository.save(payment);
        } else {
            System.out.println("[Inside Payment Service][Init Payment] Already Exists:" + payment.getId());
        }
    }

    private edu.fudan.common.util.Response<OrderTars> setOrderStatus(String tripId, String orderId) {

        int orderStatus = 1;//order paid and not collected
        edu.fudan.common.util.Response<OrderTars> result = new edu.fudan.common.util.Response<>();
        if (tripId.startsWith("G") || tripId.startsWith("D")) {
//            modifyOrder
            Holder<OrderTars> orderTarsOut = new Holder<>();
            Response response = orderControllerPrx.modifyOrder(orderId,orderStatus,orderTarsOut);
            result.setData(orderTarsOut.getValue());
            result.setMsg(response.getMsg());
            result.setStatus(response.getStatus());
        } else {
            ResponseOrder responseOrder = orderOtherControllerPrx.modifyOrder(orderId,orderStatus);
            result.setData(new OrderTars(responseOrder.getData()));
            result.setMsg(responseOrder.getMsg());
            result.setStatus(responseOrder.getStatus());
        }
        return result;
    }
}
