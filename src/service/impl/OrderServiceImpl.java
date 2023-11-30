package service.impl;

import model.entitiy.Order;
import model.enums.OrderStatus;
import repository.OrderRepository;
import repository.impl.OrderRepositoryImpl;
import service.ClientService;
import service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository repository = new OrderRepositoryImpl();
    private ClientService clientService = new ClientServiceImpl();

    public void sendOrder(Long senderId, Long recipientId) {
        Order order = new Order();
        order.setCreatedDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setSender(senderId);
        order.setRecipient(recipientId);

        repository.save(order);
    }

    public void changeOrderStatus(Long orderId, OrderStatus status) {
        Order orderToChange = getById(orderId);
        switch (status) {

            case PENDING -> orderToChange.setStatus(OrderStatus.PENDING);
            case ACCEPTED -> orderToChange.setStatus(OrderStatus.ACCEPTED);
            case CANCELED -> orderToChange.setStatus(OrderStatus.CANCELED);
            case REJECTED -> orderToChange.setStatus(OrderStatus.REJECTED);
        }
        update(orderToChange);
    }

    public Order getById(Long id) {
        return repository.findById(id);
    }

    public void update(Order order) {
        order.setUpdatedDate(LocalDateTime.now());
        repository.update(order);
    }

    @Override
    public List<Order> getSentOrders(Long sender) {
        return repository.findBySender(sender, OrderStatus.PENDING);
    }

    @Override
    public List<Order> getReceivedOrders(Long recipient) {
        return repository.findByRecipient(recipient, OrderStatus.PENDING);
    }
}
