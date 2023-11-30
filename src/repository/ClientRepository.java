package repository;

import model.entitiy.Client;
import model.entitiy.Order;

import java.util.List;

public interface ClientRepository {

    void save(Client client);
    Client findById(Long id);
    List<Client> findAll();
    void update (Client client);
    void deleteById(Long id);
    List<Client> findMatchOrders(Long id);
}
