package service;

import model.entitiy.Order;
import model.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    void sendOrder(Long senderId, Long recipientId);
    Order getById(Long id);
    void update(Order order);
    void changeOrderStatus(Long orderId, OrderStatus status);
    List<Order> getSentOrders(Long sender);
    List<Order> getReceivedOrders(Long recipient);

}
