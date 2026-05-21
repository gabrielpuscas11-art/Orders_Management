package presentation;


import javax.swing.*;
import java.awt.*;

/**
 * Meniul principal al aplicatiei de gestiune a depozitului.
 * Ofera butoane pentru deschiderea ferestrelor specifice fiecarei operatii.
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Warehouse Orders Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnClient = new JButton("Client Operations");
        JButton btnProduct = new JButton("Product Operations");
        JButton btnOrder = new JButton("Create Order");
        JButton btnBill = new JButton("Bill");

        add(btnClient);
        add(btnProduct);
        add(btnOrder);
        add(btnBill);

        btnClient.addActionListener(e -> new ClientWindow().setVisible(true));
        btnProduct.addActionListener(e -> new ProductWindow().setVisible(true));
        btnOrder.addActionListener(e -> new OrderWindow().setVisible(true));
        btnBill.addActionListener(e -> new BillLogWindow().setVisible(true));

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}