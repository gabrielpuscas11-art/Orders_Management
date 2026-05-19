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

    public List<Orders> viewAllOrders() {
        return orderDAO.findAll();
    }

    public List<Bill> viewAllBills() {
        return billDAO.findAll();
    }

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