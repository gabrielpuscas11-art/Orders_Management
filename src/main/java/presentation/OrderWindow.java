package presentation;

import BusinessLogic.OrderBLL;
import javax.swing.*;
import java.awt.*;
/**
 * Fereastra pentru crearea si plasarea unei comenzi noi.
 * Permite introducerea ID-ului de client, produs si a cantitatii.
 */
public class OrderWindow extends JFrame {
    private final JTextField txtClientId = new JTextField(5);
    private final JTextField txtProductId = new JTextField(5);
    private final JTextField txtQuantity = new JTextField(5);
    private final OrderBLL orderBLL = new OrderBLL();
    /**
     * Constructorul configureaza interfata grafica pentru comenzi.
     */
    public OrderWindow() {
        setTitle("Create Product Order");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("  Client ID:")); add(txtClientId);
        add(new JLabel("  Product ID:")); add(txtProductId);
        add(new JLabel("  Cantitate:")); add(txtQuantity);

        JButton btnPlaceOrder = new JButton("Plaseaza Comanda");


        add(new JLabel("")); add(btnPlaceOrder);


        btnPlaceOrder.addActionListener(e -> placeOrderAction());


        setLocationRelativeTo(null);
    }
    /**
     * Citeste datele introduse si apeleaza logica de plasare a comenzii.
     */
    private void placeOrderAction() {
        try {
            int clientId = Integer.parseInt(txtClientId.getText());
            int productId = Integer.parseInt(txtProductId.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            orderBLL.createOrder(clientId, productId, quantity);

            JOptionPane.showMessageDialog(this, "Comanda finalizata! Factura (Bill) a fost salvata in Log.");
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduceti valori numerice valide!", "Eroare Format", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare Stoc", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Goleste campurile de introducere a datelor.
     */
    private void clearFields() {
        txtClientId.setText("");
        txtProductId.setText("");
        txtQuantity.setText("");
    }
}