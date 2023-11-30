package service;

import model.entitiy.Client;
import model.entitiy.Order;

import java.util.List;

public interface ClientService {

    void save(Client client);
    Client getById(Long id);
    List<Client> getAll();
    void update (Client client);
    void deleteById(Long id);
}
