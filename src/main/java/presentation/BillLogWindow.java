package presentation;

import BusinessLogic.OrderBLL;
import model.Bill;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Fereastra pentru vizualizarea istoricului de facturi (Bills).
 * Afiseaza datele intr-un tabel si nu permite modificarea lor.
 */
public class BillLogWindow extends JFrame {
    private final JTable table = new JTable();
    private final OrderBLL orderBLL = new OrderBLL();
    /**
     * Constructorul configureaza interfata grafica pentru istoricul facturilor.
     */
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
    /**
     * Reincarca toate facturile emise si actualizeaza datele din tabel.
     */
    private void refreshTableAction() {
        try {
            List<Bill> bills = orderBLL.viewAllBills();
            table.setModel(TableGenerator.createTable(bills));
        } catch (Exception e) {
            table.setModel(new javax.swing.table.DefaultTableModel());
        }
    }
}