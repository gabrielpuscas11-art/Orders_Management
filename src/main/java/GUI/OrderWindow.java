package GUI;

import BusinessLogic.OrderBLL;
import presentation.TableGenerator;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Bill;

public class OrderWindow extends JFrame {
    private final JTextField txtClientId = new JTextField(5);
    private final JTextField txtProductId = new JTextField(5);
    private final JTextField txtQuantity = new JTextField(5);
    private final JTable billsTable = new JTable();
    private final OrderBLL orderBLL = new OrderBLL();

    public OrderWindow() {
        setTitle("Create Product Order and View Bills");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        topPanel.add(new JLabel("  Client ID:")); topPanel.add(txtClientId);
        topPanel.add(new JLabel("  Product ID:")); topPanel.add(txtProductId);
        topPanel.add(new JLabel("  Cantitate:")); topPanel.add(txtQuantity);

        JButton btnPlaceOrder = new JButton("Plaseaza Comanda");
        topPanel.add(new JLabel(""));
        topPanel.add(btnPlaceOrder);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(billsTable), BorderLayout.CENTER);

        btnPlaceOrder.addActionListener(e -> placeOrderAction());

        refreshBillsTable();
        setLocationRelativeTo(null);
    }

    private void placeOrderAction() {
        try {
            int clientId = Integer.parseInt(txtClientId.getText());
            int productId = Integer.parseInt(txtProductId.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            orderBLL.createOrder(clientId, productId, quantity);

            JOptionPane.showMessageDialog(this, "Comanda finalizata! Factura (Bill) a fost salvata in Log.");
            refreshBillsTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Introduceti valori numerice valide!", "Eroare Format", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare Stoc", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshBillsTable() {
        try {
            List<Bill> billList = orderBLL.viewAllBills();
            billsTable.setModel(TableGenerator.createTable(billList));
        } catch (Exception ex) {
            billsTable.setModel(new javax.swing.table.DefaultTableModel());
        }
    }

    private void clearFields() {
        txtClientId.setText("");
        txtProductId.setText("");
        txtQuantity.setText("");
    }
}