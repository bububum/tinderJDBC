package repository;

import model.entitiy.Client;
import model.entitiy.Order;
import model.enums.OrderStatus;

import java.util.List;

public interface OrderRepository {

    void save(Order order);
    Order findById(Long id);
    List<Order> findBySender(Long sender, OrderStatus status);
    void update(Order order);
    List<Order> findByRecipient(Long recipient, OrderStatus status);
}
