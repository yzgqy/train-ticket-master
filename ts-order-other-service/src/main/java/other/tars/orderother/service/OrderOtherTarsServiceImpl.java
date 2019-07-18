package other.tars.orderother.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import other.entity.*;
import other.repository.OrderOtherRepository;
import other.tars.orderother.*;
import other.tars.rpc.station.ResponseStringList;
import other.tars.rpc.station.StationControllerPrx;

import java.util.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/16 15:47
 * @Description:
 */
@Slf4j
@Service
public class OrderOtherTarsServiceImpl implements OrderOtherTarsService{
    @Autowired
    private OrderOtherRepository orderOtherRepository;

    @Autowired
    private StationControllerPrx stationControllerPrx;

    @Override
    public ResponseOrder findOrderById(String id) {
        Order order = orderOtherRepository.findById(UUID.fromString(id));
        if (order == null) {
            return new ResponseOrder(0, "No Content by this id", null);
        } else {
            return new ResponseOrder(1, "Success", order.toTars());
        }
    }

    @Override
    public ResponseOrder create(OrderTars order) {
        System.out.println("[Order Other Service][Create Order] Ready Create Order");
        ArrayList<Order> accountOrders = orderOtherRepository.findByAccountId(UUID.fromString(order.getAccountId()));
        // CreateOrderResult cor = new CreateOrderResult();
        if (accountOrders.contains(new Order(order))) {
            System.out.println("[Order Other Service][Order Create] Fail.Order already exists.");
            return new ResponseOrder(0, "Order already exist", order);
        } else {
            order.setId(UUID.randomUUID().toString());
            orderOtherRepository.save(new Order(order));
            System.out.println("[Order Other Service][Order Create] Success.");
            System.out.println("[Order Other Service][Order Create] Price:" + order.getPrice());
            return new ResponseOrder(1, "Success", order);
        }
    }

    @Override
    public ResponseOrder updateOrder(OrderTars order) {
        log.info("UPDATE ORDER INFO :" + order.toString());
        Order oldOrder = orderOtherRepository.findById(UUID.fromString(order.getId()));
        if (oldOrder == null) {
            System.out.println("[Order Service][Admin Update Order] Fail.Order not found.");
            return new ResponseOrder(0, "Order Not Found", null);
        } else {
            System.out.println(oldOrder.toString());
            oldOrder.setAccountId(UUID.fromString(order.getAccountId()));
            oldOrder.setBoughtDate(new Date(order.getBoughtDate()));
            oldOrder.setTravelDate(new Date(order.getTravelDate()));
            oldOrder.setTravelTime(new Date(order.getTravelTime()));
            oldOrder.setCoachNumber(order.getCoachNumber());
            oldOrder.setSeatClass(order.getSeatClass());
            oldOrder.setSeatNumber(order.getSeatNumber());
            oldOrder.setFrom(order.getFrom());
            oldOrder.setTo(order.getTo());
            oldOrder.setStatus(order.getStatus());
            oldOrder.setTrainNumber(order.getTrainNumber());
            oldOrder.setPrice(order.getPrice());
            oldOrder.setContactsName(order.getContactsName());
            oldOrder.setContactsDocumentNumber(order.getContactsDocumentNumber());
            oldOrder.setDocumentType(order.getDocumentType());
            orderOtherRepository.save(oldOrder);
            System.out.println("[Order Service] [Admin Update Order] Success.");
            return new ResponseOrder(1, "Success", oldOrder.toTars());
        }
    }

    @Override
    public ResponseOrder saveChanges(OrderTars order) {
        Order oldOrder = orderOtherRepository.findById(UUID.fromString(order.getId()));
        //ChangeOrderResult cor = new ChangeOrderResult();
        if (oldOrder == null) {
            System.out.println("[Order Other Service][Modify Order] Fail.Order not found.");
            return new ResponseOrder(0, "Order Not Found", null);
        } else {
            oldOrder.setAccountId(UUID.fromString(order.getAccountId()));
            oldOrder.setBoughtDate(new Date(order.getBoughtDate()));
            oldOrder.setTravelDate(new Date(order.getTravelDate()));
            oldOrder.setTravelTime(new Date(order.getTravelTime()));
            oldOrder.setSeatClass(order.getSeatClass());
            oldOrder.setCoachNumber(order.getCoachNumber());

            oldOrder.setSeatNumber(order.getSeatNumber());
            oldOrder.setTo(order.getTo());
            oldOrder.setFrom(order.getFrom());
            oldOrder.setStatus(order.getStatus());
            oldOrder.setTrainNumber(order.getTrainNumber());
            oldOrder.setPrice(order.getPrice());
            oldOrder.setContactsName(order.getContactsName());
            oldOrder.setDocumentType(order.getDocumentType());
            oldOrder.setContactsDocumentNumber(order.getContactsDocumentNumber());

            orderOtherRepository.save(oldOrder);
            System.out.println("[Order Other Service] Success.");
            return new ResponseOrder(1, "Success", oldOrder.toTars());
        }
    }

    @Override
    public ResponseOrder cancelOrder(String accountId, String orderId) {

        Order oldOrder = orderOtherRepository.findById(UUID.fromString(orderId));
        if (oldOrder == null) {
            System.out.println("[Order Other Service][Cancel Order] Fail.Order not found.");
            return new ResponseOrder(0, "Order Not Found", null);
        } else {
            oldOrder.setStatus(OrderStatus.CANCEL.getCode());
            orderOtherRepository.save(oldOrder);
            System.out.println("[Order Other Service][Cancel Order] Success.");
            return new ResponseOrder(1, "Success", oldOrder.toTars());
        }
    }

    @Override
    public ResponseOrder addNewOrder(OrderTars order) {
        System.out.println("[Order Service][Admin Add Order] Ready Add Order.");
        ArrayList<Order> accountOrders = orderOtherRepository.findByAccountId(UUID.fromString(order.getAccountId()));
        // AddOrderResult result = new AddOrderResult();
        if (accountOrders.contains(new Order(order))) {
            System.out.println("[Order Service][Admin Add Order] Fail.Order already exists.");
            return new ResponseOrder(0, "Order already exist", null);
        } else {
            order.setId(UUID.randomUUID().toString());
            orderOtherRepository.save(new Order(order));
            System.out.println("[Order Service][Admin Add Order] Success.");
            System.out.println("[Order Service][Admin Add Order] Price:" + order.getPrice());
            return new ResponseOrder(1, "Success", order);
        }
    }

    @Override
    public ResponseString deleteOrder(String orderId) {
        UUID orderUuid = UUID.fromString(orderId);
        Order order = orderOtherRepository.findById(orderUuid);
        if (order == null) {
            return new ResponseString(0, "Order Not Exist.", null);
        } else {
            orderOtherRepository.deleteById(orderUuid);
            return new ResponseString(1, "Success.", orderUuid.toString());
        }
    }

    @Override
    public ResponseOrder getOrderById(String orderId) {
        Order order = orderOtherRepository.findById(UUID.fromString(orderId));
        if (order == null) {
            return new ResponseOrder(0, "Order Not Found", null);
        } else {
            return new ResponseOrder(1, "Success", order.toTars());
        }
    }

    @Override
    public ResponseOrder payOrder(String orderId) {
        Order order = orderOtherRepository.findById(UUID.fromString(orderId));
        if (order == null) {
            return new ResponseOrder(0, "Order Not Found", null);
        } else {
            order.setStatus(OrderStatus.PAID.getCode());
            orderOtherRepository.save(order);
            return new ResponseOrder(1, "Success.", order.toTars());
        }
    }

    @Override
    public ResponseString getOrderPrice(String orderId) {
        Order order = orderOtherRepository.findById(UUID.fromString(orderId));
        if (order == null) {
            return new ResponseString(0, "Order Not Found", "-1.0");
        } else {
            System.out.println("[Order Other Service][Get Order Price] Price:" + order.getPrice());
            return new ResponseString(1, "Success", order.getPrice());
        }
    }

    @Override
    public ResponseOrder modifyOrder(String orderId, int status) {
        Order order = orderOtherRepository.findById(UUID.fromString(orderId));
        // ModifyOrderStatusResult result = new ModifyOrderStatusResult();
        if (order == null) {
            return new ResponseOrder(0, "Order Not Found", null);
        } else {
            order.setStatus(status);
            orderOtherRepository.save(order);
            return new ResponseOrder(1, "Success", order.toTars());
        }
    }

    @Override
    public ResponseOrderList getAllOrders() {
        ArrayList<Order> orders = orderOtherRepository.findAll();
        List<OrderTars> orderTarsList = new ArrayList<>();
        if (orders == null) {
            return new ResponseOrderList(0, "No Content", null);
        } else {
            for(Order order:orders){
                orderTarsList.add(order.toTars());
            }
            return new ResponseOrderList(1, "Success.", orderTarsList);
        }
    }

    @Override
    public ResponseLeftTicketInfo getSoldTickets(SeatTars seatRequest) {
        ArrayList<Order> list = orderOtherRepository.findByTravelDateAndTrainNumber(new Date(seatRequest.getTravelDate()),
                seatRequest.getTrainNumber());
        if (list != null && list.size() > 0) {
            Set ticketSet = new HashSet();
            for (Order tempOrder : list) {
                Ticket ticket = new Ticket();
                ticket.setSeatNo(Integer.parseInt(tempOrder.getSeatNumber()));
                ticket.setStartStation(tempOrder.getFrom());
                ticket.setDestStation(tempOrder.getTo());
                ticketSet.add(ticket);
            }

            LeftTicketInfo leftTicketInfo = new LeftTicketInfo();
            leftTicketInfo.setSoldTickets(ticketSet);
            System.out.println("Left ticket info is: " + leftTicketInfo.toString());

            return new ResponseLeftTicketInfo(1, "Success", leftTicketInfo.toTars());
        } else {
            return new ResponseLeftTicketInfo(0, "Seat is Null.", null);
        }
    }

    @Override
    public ResponseOrderList queryOrders(QueryInfoTars qi, String accountId) {
        //1.Get all orders of the user
        ArrayList<Order> list = orderOtherRepository.findByAccountId(UUID.fromString(accountId));
        System.out.println("[Order Other Service][Query Order][Step 1] Get Orders Number of Account:" + list.size());
        //2.Check is these orders fit the requirement/
        if (qi.isEnableStateQuery() || qi.isEnableBoughtDateQuery() || qi.isEnableTravelDateQuery()) {
            ArrayList<Order> finalList = new ArrayList<>();
            ArrayList<OrderTars> finalTarsList = new ArrayList<>();
            for (Order tempOrder : list) {
                boolean statePassFlag = false;
                boolean boughtDatePassFlag = false;
                boolean travelDatePassFlag = false;
                //3.Check order state requirement.
                if (qi.isEnableStateQuery()) {
                    if (tempOrder.getStatus() != qi.getState()) {
                        statePassFlag = false;
                    } else {
                        statePassFlag = true;
                    }
                } else {
                    statePassFlag = true;
                }
                System.out.println("[Order Other Service][Query Order][Step 2][Check Status Fits End]");
                //4.Check order travel date requirement.
                if (qi.isEnableTravelDateQuery()) {
                    if (tempOrder.getTravelDate().before(new Date(qi.getTravelDateEnd())) &&
                            tempOrder.getTravelDate().after(new Date(qi.getBoughtDateStart()))) {
                        travelDatePassFlag = true;
                    } else {
                        travelDatePassFlag = false;
                    }
                } else {
                    travelDatePassFlag = true;
                }
                System.out.println("[Order Other Service][Query Order][Step 2][Check Travel Date End]");
                //5.Check order bought date requirement.
                if (qi.isEnableBoughtDateQuery()) {
                    if (tempOrder.getBoughtDate().before(new Date(qi.getBoughtDateEnd())) &&
                            tempOrder.getBoughtDate().after(new Date(qi.getBoughtDateStart()))) {
                        boughtDatePassFlag = true;
                    } else {
                        boughtDatePassFlag = false;
                    }
                } else {
                    boughtDatePassFlag = true;
                }
                System.out.println("[Order Other Service][Query Order][Step 2][Check Bought Date End]");
                //6.check if all requirement fits.
                if (statePassFlag && boughtDatePassFlag && travelDatePassFlag) {
                    finalList.add(tempOrder);
                }
                System.out.println("[Order Other Service][Query Order][Step 2][Check All Requirement End]");
            }
            System.out.println("[Order Other Service][Query Order] Get order num:" + finalList.size());
            for(Order order:finalList){
                finalTarsList.add(order.toTars());
            }
            return new ResponseOrderList(1, "Get order num", finalTarsList);
        } else {
            System.out.println("[Order Other Service][Query Order] Get order num:" + list.size());
            List<OrderTars> listTars = new ArrayList<>();
            if(list!=null)
                for(Order order:list){
                    listTars.add(order.toTars());
                }
            return new ResponseOrderList(1, "Get order num", listTars);
        }
    }

    @Override
    public ResponseOrderList queryOrdersForRefresh(QueryInfoTars qi, String accountId) {
        List<OrderTars> orders = queryOrders(qi, accountId).getData();
        ArrayList<String> stationIds = new ArrayList<>();
        for (OrderTars order : orders) {
            stationIds.add(order.getFrom());
            stationIds.add(order.getTo());
        }
        List<String> names = queryForStationId(stationIds);
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setFrom(names.get(i * 2));
            orders.get(i).setTo(names.get(i * 2 + 1));
        }
        return new ResponseOrderList(1, "Success", orders);

    }
    public List<String> queryForStationId(List<String> ids) {
        ResponseStringList re = stationControllerPrx.queryForNameBatch(ids);
        System.out.println("Stations name list is : " + re.toString());
        List<String> names = re.getData();
        return names;
    }

    @Override
    public ResponseOrder alterOrder(OrderAlterInfo oai) {
        UUID oldOrderId = oai.getPreviousOrderId();
        Order oldOrder = orderOtherRepository.findById(oldOrderId);
        if (oldOrder == null) {
            System.out.println("[Order Other Service][Alter Order] Fail.Order do not exist.");
            return new ResponseOrder(0, "Old Order Does Not Exists", null);
        }
        oldOrder.setStatus(OrderStatus.CANCEL.getCode());
        saveChanges(oldOrder.toTars());
        Order newOrder = oai.getNewOrderInfo();
        newOrder.setId(UUID.randomUUID());
        ResponseOrder cor = create(oai.getNewOrderInfo().toTars());
        if (cor.getStatus() == 1) {
            System.out.println("[Order Other Service][Alter Order] Success.");
            return new ResponseOrder(1, "Alter Order Success", newOrder.toTars());
        } else {
            return new ResponseOrder(0, cor.getMsg(), null);
        }
    }

    @Override
    public ResponseSoldTicket queryAlreadySoldOrders(long travelDate, String trainNumber) {
        ArrayList<Order> orders = orderOtherRepository.findByTravelDateAndTrainNumber(new Date(travelDate), trainNumber);
        SoldTicket cstr = new SoldTicket();
        cstr.setTravelDate(new Date(travelDate));
        cstr.setTrainNumber(trainNumber);
        System.out.println("[Order Other Service][Calculate Sold Ticket] Get Orders Number:" + orders.size());
        for (Order order : orders) {
            if (order.getStatus() >= OrderStatus.CHANGE.getCode()) {
                continue;
            }
            if (order.getSeatClass() == SeatClass.NONE.getCode()) {
                cstr.setNoSeat(cstr.getNoSeat() + 1);
            } else if (order.getSeatClass() == SeatClass.BUSINESS.getCode()) {
                cstr.setBusinessSeat(cstr.getBusinessSeat() + 1);
            } else if (order.getSeatClass() == SeatClass.FIRSTCLASS.getCode()) {
                cstr.setFirstClassSeat(cstr.getFirstClassSeat() + 1);
            } else if (order.getSeatClass() == SeatClass.SECONDCLASS.getCode()) {
                cstr.setSecondClassSeat(cstr.getSecondClassSeat() + 1);
            } else if (order.getSeatClass() == SeatClass.HARDSEAT.getCode()) {
                cstr.setHardSeat(cstr.getHardSeat() + 1);
            } else if (order.getSeatClass() == SeatClass.SOFTSEAT.getCode()) {
                cstr.setSoftSeat(cstr.getSoftSeat() + 1);
            } else if (order.getSeatClass() == SeatClass.HARDBED.getCode()) {
                cstr.setHardBed(cstr.getHardBed() + 1);
            } else if (order.getSeatClass() == SeatClass.SOFTBED.getCode()) {
                cstr.setSoftBed(cstr.getSoftBed() + 1);
            } else if (order.getSeatClass() == SeatClass.HIGHSOFTBED.getCode()) {
                cstr.setHighSoftBed(cstr.getHighSoftBed() + 1);
            } else {
                System.out.println("[Order Other Service][Calculate Sold Tickets] Seat class not exists. Order ID:" + order.getId());
            }
        }
        return new ResponseSoldTicket(1, "Success", cstr.toTars());

    }

    @Override
    public ResponseOrderSecurity checkSecurityAboutOrder(long dateFrom, String accountId) {
        OrderSecurity result = new OrderSecurity();
        ArrayList<Order> orders = orderOtherRepository.findByAccountId(UUID.fromString(accountId));
        int countOrderInOneHour = 0;
        int countTotalValidOrder = 0;
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date(dateFrom));
        ca.add(Calendar.HOUR_OF_DAY, -1);
        dateFrom = ca.getTime().getTime();
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.NOTPAID.getCode() ||
                    order.getStatus() == OrderStatus.PAID.getCode() ||
                    order.getStatus() == OrderStatus.COLLECTED.getCode()) {
                countTotalValidOrder += 1;
            }
            if (order.getBoughtDate().after(new Date(dateFrom))) {
                countOrderInOneHour += 1;
            }
        }
        result.setOrderNumInLastOneHour(countOrderInOneHour);
        result.setOrderNumOfValidOrder(countTotalValidOrder);
        return new ResponseOrderSecurity(1, "Success . ", result.toTars());

    }

    @Override
    public void initOrder(OrderTars order) {
        Order orderTemp = orderOtherRepository.findById(UUID.fromString(order.getId()));
        if (orderTemp == null) {
            orderOtherRepository.save(new Order(order));
        } else {
            System.out.println("[Order Other Service][Init Order] Order Already Exists ID:" + order.getId());
        }
    }
}
