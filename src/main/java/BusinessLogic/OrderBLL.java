package BusinessLogic;

import DataAcces.*;
import model.Bill;
import model.*;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final ClientDAO clientDAO = new ClientDAO();
    private final BillDAO billDAO = new BillDAO();
    /**
     * Returneaza o lista cu toate comenzile plasate in sistem.
     *
     * @return Lista de obiecte Orders
     */
    public List<Orders> viewAllOrders() {
        return orderDAO.findAll();
    }
    /**
     * Returneaza istoricul tuturor facturilor (Bills) generate.
     *
     * @return Lista de obiecte Bill
     */
    public List<Bill> viewAllBills() {
        return billDAO.findAll();
    }
    /**
     * Creeaza o comanda noua, scade stocul produsului si genereaza factura aferenta.
     * Metoda este sincronizata pentru a preveni problemele de concurenta pe stoc.
     *
     * @param clientId ID-ul clientului care comanda
     * @param productId ID-ul produsului comandat
     * @param requestedQuantity Cantitatea solicitata
     * @throws NoSuchElementException Daca clientul sau produsul nu exista
     * @throws IllegalArgumentException Daca stocul este mai mic decat cantitatea ceruta
     */
    public synchronized void createOrder(int clientId, int productId, int requestedQuantity) {
        Client client = clientDAO.findById(clientId);
        Product product = productDAO.findById(productId);

        if (client == null || product == null) {
            throw new NoSuchElementException("Clientul sau produsul selectat nu exista!");
        }
        if (product.getStock() < requestedQuantity) {
            throw new IllegalArgumentException("Under-stock! Stoc insuficient pentru " + product.getName());
        }


        product.setStock(product.getStock() - requestedQuantity);
        productDAO.update(product);


        Orders orders = new Orders(0, clientId, productId, requestedQuantity);
        Orders saveorders = orderDAO.insert(orders);


        double totalPrice = product.getPrice() * requestedQuantity;
        Bill bill = new Bill(saveorders.getId(), client.getName(), product.getName(), requestedQuantity, totalPrice);

        billDAO.insert(bill);
    }
}