package BusinessLogic;

import DataAcces.ClientDAO;
import model.Client;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private final ClientDAO clientDAO = new ClientDAO();

    public List<Client> viewAllClients() {
        List<Client> clients = clientDAO.findAll();
        if (clients.isEmpty()) {
            throw new NoSuchElementException("Nu exista niciun client în baza de date!");
        }
        return clients;
    }

    public void insertClient(Client client) {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele clientului nu poate fi gol!");
        }
        clientDAO.insert(client);
    }

    public void updateClient(Client client) {
        if (clientDAO.findById(client.getId()) == null) {
            throw new NoSuchElementException("Clientul cu ID-ul " + client.getId() + " nu exista!");
        }
        clientDAO.update(client);
    }

    public void deleteClient(int id) {
        if (!clientDAO.delete(id)) {
            throw new NoSuchElementException("Stergerea a esuat! Clientul cu ID-ul " + id + " nu exista.");
        }
    }
}