package GUI;

import presentation.TableGenerator;
import BusinessLogic.OrderBLL;
import model.Bill;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BillLogWindow extends JFrame {
    private final JTable table = new JTable();
    private final OrderBLL orderBLL = new OrderBLL();

    public BillLogWindow() {
        setTitle("Bills History Log (Immutable)");
        setSize(600, 450);
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> refreshTableAction());

        refreshTableAction();
        setLocationRelativeTo(null);
    }

    private void refreshTableAction() {
        try {
            List<Bill> bills = orderBLL.viewAllBills();
            table.setModel(TableGenerator.createTable(bills));
        } catch (Exception e) {
            table.setModel(new javax.swing.table.DefaultTableModel());
        }
    }
}