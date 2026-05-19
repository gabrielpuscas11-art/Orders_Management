package GUI;

import presentation.TableGenerator;
import BusinessLogic.ClientBLL;
import model.Client;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ClientWindow extends JFrame {
    private final JTextField txtId = new JTextField(5);
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtEmail = new JTextField(15);
    private final JTextField txtAddress = new JTextField(15);
    private final JTable table = new JTable();
    private final ClientBLL clientBLL = new ClientBLL();

    public ClientWindow() {
        setTitle("Client Operations");
        setSize(800, 500);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("ID:")); inputPanel.add(txtId);
        inputPanel.add(new JLabel("Nume:")); inputPanel.add(txtName);
        inputPanel.add(new JLabel("Email:")); inputPanel.add(txtEmail);
        inputPanel.add(new JLabel("Adresa:")); inputPanel.add(txtAddress);


        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("View All");

        buttonPanel.add(btnAdd); buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete); buttonPanel.add(btnRefresh);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        btnAdd.addActionListener(e -> addClientAction());
        btnEdit.addActionListener(e -> editClientAction());
        btnDelete.addActionListener(e -> deleteClientAction());
        btnRefresh.addActionListener(e -> refreshTableAction());

        refreshTableAction();
    }

    private void addClientAction() {
        try {
            Client c = new Client(0, txtName.getText(), txtEmail.getText(), txtAddress.getText());
            clientBLL.insertClient(c);
            refreshTableAction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editClientAction() {
        try {
            int id = Integer.parseInt(txtId.getText());
            Client c = new Client(id, txtName.getText(), txtEmail.getText(), txtAddress.getText());
            clientBLL.updateClient(c);
            refreshTableAction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteClientAction() {
        try {
            int id = Integer.parseInt(txtId.getText());
            clientBLL.deleteClient(id);
            refreshTableAction();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTableAction() {
        try {
            List<Client> list = clientBLL.viewAllClients();
            table.setModel(TableGenerator.createTable(list));
        } catch (Exception ex) {
            table.setModel(new javax.swing.table.DefaultTableModel());
        }
    }
}
