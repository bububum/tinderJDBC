package service.impl;

import model.entitiy.Client;
import repository.ClientRepository;
import repository.impl.ClientRepositoryImpl;
import service.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    ClientRepository repository = new ClientRepositoryImpl();

    @Override
    public void save(Client client) {
        repository.save(client);
    }

    @Override
    public Client getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(Client client) {
        repository.update(client);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
