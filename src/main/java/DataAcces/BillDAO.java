package DataAcces;


import model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public void insert(Bill bill) {
        String query = "INSERT INTO bill (orderId, clientName, productName, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setInt(4, bill.quantity());
            statement.setDouble(5, bill.totalPrice());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT orderId, clientName, productName, quantity, totalPrice FROM bill";

        try (Connection connection = ConnnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                int orderId = resultSet.getInt("orderId");
                String clientName = resultSet.getString("clientName");
                String productName = resultSet.getString("productName");
                int quantity = resultSet.getInt("quantity");
                double totalPrice = resultSet.getDouble("totalPrice");


                Bill bill = new Bill(orderId, clientName, productName, quantity, totalPrice);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
}
