package GUI;

import presentation.TableGenerator;
import BusinessLogic.OrderBLL;
import model.Bill;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BillLogWindow extends JFrame {
    private JTable table = new JTable();
    private OrderBLL orderBLL = new OrderBLL();

    public BillLogWindow() {
        setTitle("Bills History Log (Immutable)");
        setSize(600, 400);
        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);

        try {
            List<Bill> bills = orderBLL.viewAllBills();
            table.setModel(TableGenerator.createTable(bills));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLocationRelativeTo(null);
    }
}