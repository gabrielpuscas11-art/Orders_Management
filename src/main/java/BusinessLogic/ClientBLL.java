package BusinessLogic;

import DataAcces.ClientDAO;
import model.Client;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private final ClientDAO clientDAO = new ClientDAO();
    /**
     * Returneaza o lista cu toti clientii existenti in baza de date.
     *
     * @return Lista de obiecte Client
     * @throws NoSuchElementException Daca nu exista niciun client in baza de date
     */
    public List<Client> viewAllClients() {
        List<Client> clients = clientDAO.findAll();
        if (clients.isEmpty()) {
            throw new NoSuchElementException("Nu exista niciun client în baza de date!");
        }
        return clients;
    }
    /**
     * Valideaza numele si insereaza un client nou in sistem.
     *
     * @param client Clientul care urmeaza sa fie adaugat
     * @throws IllegalArgumentException Daca numele clientului este gol sau null
     */
    public void insertClient(Client client) {
        if (client.getName() == null || client.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele clientului nu poate fi gol!");
        }
        clientDAO.insert(client);
    }
    /**
     * Actualizeaza datele unui client existent, dupa verificarea ID-ului.
     *
     * @param client Clientul modificat
     * @throws NoSuchElementException Daca clientul nu este gasit in baza de date
     */
    public void updateClient(Client client) {
        if (clientDAO.findById(client.getId()) == null) {
            throw new NoSuchElementException("Clientul cu ID-ul " + client.getId() + " nu exista!");
        }
        clientDAO.update(client);
    }
    /**
     * Sterge un client din baza de date pe baza ID-ului furnizat.
     *
     * @param id Identificatorul unic al clientului de sters
     * @throws NoSuchElementException Daca stergerea a esuat din cauza unui ID inexistent
     */    public void deleteClient(int id) {
        if (!clientDAO.delete(id)) {
            throw new NoSuchElementException("Stergerea a esuat! Clientul cu ID-ul " + id + " nu exista.");
        }
    }
}