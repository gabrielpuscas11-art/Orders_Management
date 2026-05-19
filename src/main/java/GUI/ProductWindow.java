package GUI;

import presentation.TableGenerator;
import BusinessLogic.ProductBLL;
import model.Product;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ProductWindow extends JFrame {
        private final JTextField txtId = new JTextField(5);
        private final JTextField txtName = new JTextField(15);
        private final JTextField txtPrice = new JTextField(10);
        private final JTextField txtStock = new JTextField(10);
        private final JTable table = new JTable();
        private final ProductBLL productBLL = new ProductBLL();

        public ProductWindow() {
            setTitle("Product Operations");
            setSize(600, 400);
            setLayout(new BorderLayout());


            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("ID:")); inputPanel.add(txtId);
            inputPanel.add(new JLabel("Nume:")); inputPanel.add(txtName);
            inputPanel.add(new JLabel("Pret:")); inputPanel.add(txtPrice);
            inputPanel.add(new JLabel("Stoc:")); inputPanel.add(txtStock);


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


            btnAdd.addActionListener(e -> addProductAction());
            btnEdit.addActionListener(e -> editProductAction());
            btnDelete.addActionListener(e -> deleteProductAction());
            btnRefresh.addActionListener(e -> refreshTableAction());

            refreshTableAction();
            setLocationRelativeTo(null);
        }

        private void addProductAction() {
            try {
                double price = Double.parseDouble(txtPrice.getText());
                int stock = Integer.parseInt(txtStock.getText());
                Product p = new Product(0, txtName.getText(), price, stock);

                productBLL.insertProduct(p);
                refreshTableAction();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Prtul și stocul trebuie sa fie numere valide!", "Eroare Format", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void editProductAction() {
            try {
                int id = Integer.parseInt(txtId.getText());
                double price = Double.parseDouble(txtPrice.getText());
                int stock = Integer.parseInt(txtStock.getText());
                Product p = new Product(id, txtName.getText(), price, stock);

                productBLL.updateProduct(p);
                refreshTableAction();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verificati ID-ul, pretul și stocul sa fie corecte!", "Eroare Format", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void deleteProductAction() {
            try {
                int id = Integer.parseInt(txtId.getText());
                productBLL.deleteProduct(id);
                refreshTableAction();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Va rugam introduceti un ID valid pentru stergere!", "Eroare Format", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void refreshTableAction() {
            try {
                List<Product> list = productBLL.viewAllProducts();
                table.setModel(TableGenerator.createTable(list));
            } catch (Exception ex) {
                table.setModel(new javax.swing.table.DefaultTableModel());
            }
        }

        private void clearFields() {
            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");
            txtStock.setText("");
        }
}
