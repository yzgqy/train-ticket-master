package order.tars.order.service;

import edu.fudan.common.util.Response;
import lombok.extern.slf4j.Slf4j;
import order.entity.*;
import order.repository.OrderRepository;
import order.tars.order.*;
import order.tars.rpc.station.ResponseStringList;
import order.tars.rpc.station.StationControllerPrx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: yaya
 * @Date: 2019/7/17 18:21
 * @Description:
 */
@Service
@Slf4j
public class OrderTarsServiceImpl implements OrderTarsService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StationControllerPrx stationControllerPrx;

    @Override
    public Response<OrderTars> findOrderById(UUID id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            return new Response<OrderTars>(0, "No Content by this id", null);
        } else {
            return new Response<OrderTars>(1, "Success", order.toTars());
        }
    }

    @Override
    public Response<OrderTars> create(OrderTars order) {
        System.out.println("[Order Service][Create Order] Ready Create Order.");
        ArrayList<Order> accountOrders = orderRepository.findByAccountId(UUID.fromString(order.getAccountId()));
        //CreateOrderResult cor = new CreateOrderResult();
        if (accountOrders.contains(new Order(order))) {
            System.out.println("[Order Service][Order Create] Fail.Order already exists.");
            return new Response<>(0, "Order already exist", null);
        } else {
            order.setId(UUID.randomUUID().toString());
            orderRepository.save(new Order(order));
            System.out.println("[Order Service][Order Create] Success.");
            System.out.println("[Order Service][Order Create] Price:" + order.getPrice());
            return new Response<>(1, "Success", order);
        }
    }

    @Override
    public Response<OrderTars> saveChanges(OrderTars order) {
        Order oldOrder = orderRepository.findById(UUID.fromString(order.getId()));
        if (oldOrder == null) {
            System.out.println("[Order Service][Modify Order] Fail.Order not found.");
            return new Response<>(0, "Order Not Found", null);
        } else {
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
            orderRepository.save(oldOrder);
            System.out.println("[Order Service] Success.");
            return new Response<OrderTars>(1, "Success", oldOrder.toTars());
        }
    }

    @Override
    public Response<OrderTars> cancelOrder(UUID accountId, UUID orderId) {
        Order oldOrder = orderRepository.findById(orderId);
        if (oldOrder == null) {
            System.out.println("[Cancel Service][Cancel Order] Fail.Order not found.");
            return new Response<>(0, "Order Not Found", null);
        } else {
            oldOrder.setStatus(OrderStatus.CANCEL.getCode());
            orderRepository.save(oldOrder);
            System.out.println("[Cancel Service][Cancel Order] Success.");
            return new Response<>(1, "Success", oldOrder.toTars());
        }
    }

    @Override
    public Response<List<OrderTars>> queryOrders(OrderInfoTars qi, String accountId) {
        //1.Get all orders of the user
        ArrayList<Order> list = orderRepository.findByAccountId(UUID.fromString(accountId));
        List<OrderTars>  orderTarsList = new ArrayList<>();
        System.out.println("[Order Service][Query Order][Step 1] Get Orders Number of Account:" + list.size());
        //2.Check is these orders fit the requirement/
        if (qi.isEnableStateQuery() || qi.isEnableBoughtDateQuery() || qi.isEnableTravelDateQuery()) {
            ArrayList<Order> finalList = new ArrayList<>();
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
                System.out.println("[Order Service][Query Order][Step 2][Check Status Fits End]");
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
                System.out.println("[Order Service][Query Order][Step 2][Check Travel Date End]");
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
                System.out.println("[Order Service][Query Order][Step 2][Check Bought Date End]");
                //6.check if all requirement fits.
                if (statePassFlag && boughtDatePassFlag && travelDatePassFlag) {
                    finalList.add(tempOrder);
                }
                System.out.println("[Order Service][Query Order][Step 2][Check All Requirement End]");
            }
            System.out.println("[Order Service][Query Order] Get order num:" + finalList.size());
            for(Order order:finalList)
                orderTarsList.add(order.toTars());
            return new Response<>(1, "Get order num", orderTarsList);
        } else {
            if(list != null&&list.size()>0)
                for(Order order:list)
                    orderTarsList.add(order.toTars());
            System.out.println("[Order Service][Query Order] Get order num:" + list.size());
            return new Response<>(1, "Get order num", orderTarsList);
        }
    }

    @Override
    public Response<List<OrderTars>> queryOrdersForRefresh(OrderInfoTars qi, String accountId) {
        List<OrderTars> orders =   queryOrders(qi, accountId).getData();
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
        return new Response<>(1, "Query Orders For Refresh Success", orders);

    }

    @Override
    public Response<OrderTars> alterOrder(OrderAlterInfo oai) {
        UUID oldOrderId = oai.getPreviousOrderId();
        Order oldOrder = orderRepository.findById(oldOrderId);
        if (oldOrder == null) {
            System.out.println("[Order Service][Alter Order] Fail.Order do not exist.");
            return new Response<>(0, "Old Order Does Not Exists", null);
        }
        oldOrder.setStatus(OrderStatus.CANCEL.getCode());
        saveChanges(oldOrder.toTars());
        Order newOrder = oai.getNewOrderInfo();
        newOrder.setId(UUID.randomUUID());
        Response cor = create(oai.getNewOrderInfo().toTars());
        if (cor.getStatus() == 1) {
            System.out.println("[Order Service][Alter Order] Success.");
            return new Response<>(1, "Success", newOrder.toTars());
        } else {
            return new Response<>(0, cor.getMsg(), null);
        }
    }

    @Override
    public Response<SoldTicketTars> queryAlreadySoldOrders(long travelDate, String trainNumber) {
        ArrayList<Order> orders = orderRepository.findByTravelDateAndTrainNumber(new Date(travelDate), trainNumber);
        SoldTicket cstr = new SoldTicket();
        cstr.setTravelDate(new Date(travelDate));
        cstr.setTrainNumber(trainNumber);
        System.out.println("[Order Service][Calculate Sold Ticket] Get Orders Number:" + orders.size());
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
                System.out.println("[Order Service][Calculate Sold Tickets] Seat class not exists. Order ID:" + order.getId());
            }
        }
        return new Response<>(1, "Success", cstr.toTars());

    }

    @Override
    public Response<List<OrderTars>> getAllOrders() {
        ArrayList<Order> orders = orderRepository.findAll();
        List<OrderTars> orderTarsList = new ArrayList<>();
        if (orders == null) {
            return new Response<>(0, "No Content", null);
        } else {
            for(Order order:orders){
                orderTarsList.add(order.toTars());
            }
            return new Response<>(1, "Success.", orderTarsList);
        }
    }

    @Override
    public Response<OrderTars> modifyOrder(String orderId, int status) {
        Order order = orderRepository.findById(UUID.fromString(orderId));
        if (order == null) {
            return new Response<>(0, "Order Not Found", null);
        } else {
            order.setStatus(status);
            orderRepository.save(order);
            return new Response<>(1, "Modify Order Success", order.toTars());
        }
    }

    @Override
    public Response<String> getOrderPrice(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId));
        if (order == null) {
            System.out.println("[Other Service][Get Order Price] Order Not Found.");
            return new Response<>(0, "Order Not Found", "-1.0");
        } else {
            System.out.println("[Order Service][Get Order Price] Price:" + order.getPrice());
            return new Response<>(1, "Success", order.getPrice());
        }
    }

    @Override
    public Response<OrderTars> payOrder(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId));
        //PayOrderResult result = new PayOrderResult();
        if (order == null) {
            return new Response<>(0, "Order Not Found", null);
        } else {
            order.setStatus(OrderStatus.PAID.getCode());
            orderRepository.save(order);
            return new Response<>(1, "Pay Order Success.", order.toTars());
        }
    }

    @Override
    public Response<OrderTars> getOrderById(String orderId) {
        Order order = orderRepository.findById(UUID.fromString(orderId));
        if (order == null) {
            return new Response<>(0, "Order Not Found", null);
        } else {
            return new Response<>(1, "Success.", order.toTars());
        }
    }

    @Override
    public Response<OrderSecurityTars> checkSecurityAboutOrder(long dateFrom, String accountId) {
        OrderSecurity result = new OrderSecurity();
        ArrayList<Order> orders = orderRepository.findByAccountId(UUID.fromString(accountId));
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
        return new Response<>(1, "Check Security Success . ", result.toTars());

    }

    @Override
    public void initOrder(OrderTars order) {
        Order orderTemp = orderRepository.findById(UUID.fromString(order.getId()));
        if (orderTemp == null) {
            orderRepository.save(new Order(order));
        } else {
            System.out.println("[Order Service][Init Order] Order Already Exists ID:" + order.getId());
        }
    }

    @Override
    public Response<OrderTars> deleteOrder(String orderId) {
        UUID orderUuid = UUID.fromString(orderId);
        Order order = orderRepository.findById(orderUuid);

        if (order == null) {
            return new Response<>(0, "Order Not Exist.", null);
        } else {
            orderRepository.deleteById(orderUuid);
            return new Response<>(1, "Delete Order Success", order.toTars());
        }
    }

    @Override
    public Response<LeftTicketInfoTars> getSoldTickets(SeatTars seatRequest) {
        ArrayList<Order> list = orderRepository.findByTravelDateAndTrainNumber(new Date(seatRequest.getTravelDate()),
                seatRequest.getTrainNumber());
        if (list != null && list.size() > 0) {
            Set ticketSet = new HashSet();
            for (Order tempOrder : list) {
                ticketSet.add(new Ticket(Integer.parseInt(tempOrder.getSeatNumber()),
                        tempOrder.getFrom(), tempOrder.getTo()));
            }
            LeftTicketInfo leftTicketInfo = new LeftTicketInfo();
            leftTicketInfo.setSoldTickets(ticketSet);
            System.out.println("Left ticket info is: " + leftTicketInfo.toString());
            return new Response<>(1, "Success", leftTicketInfo.toTars());
        } else {
            System.out.println("Left ticket info is empty");
            return new Response<>(0, "Order is Null.", null);
        }
    }

    @Override
    public Response<OrderTars> addNewOrder(OrderTars order) {
        System.out.println("[Order Service][Admin Add Order] Ready Add Order.");
        ArrayList<Order> accountOrders = orderRepository.findByAccountId(UUID.fromString(order.getAccountId()));
        if (accountOrders.contains(order)) {
            System.out.println("[Order Service][Admin Add Order] Fail.Order already exists.");
            return new Response<>(0, "Order already exist", null);
        } else {
            order.setId(UUID.randomUUID().toString());
            orderRepository.save(new Order(order));
            System.out.println("[Order Service][Admin Add Order] Success.");
            System.out.println("[Order Service][Admin Add Order] Price:" + order.getPrice());
            return new Response<>(1, "Add new Order Success", order);
        }
    }

    @Override
    public Response<OrderTars> updateOrder(OrderTars order) {
        log.info("UPDATE ORDER INFO: " +order.toString());
        Order oldOrder = orderRepository.findById(UUID.fromString(order.getId()));
        if (oldOrder == null) {
            System.out.println("[Order Service][Admin Update Order] Fail.Order not found.");
            return new Response<>(0, "Order Not Found, Can't update", null);
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
            orderRepository.save(oldOrder);
            System.out.println("[Order Service] [Admin Update Order] Success.");
            return new Response<>(1, "Admin Update Order Success", oldOrder.toTars());
        }
    }

    public List<String> queryForStationId(List<String> ids) {
        ResponseStringList re = stationControllerPrx.queryForNameBatch(ids);
        System.out.println("Stations name list is : " + re.toString());
        List<String> names = re.getData();
        return names;
    }

}
