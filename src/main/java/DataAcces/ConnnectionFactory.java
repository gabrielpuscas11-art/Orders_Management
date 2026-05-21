package DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnnectionFactory {
    private static final String DRIVER ="org.postgresql.Driver";
    private static final String URL ="jdbc:postgresql://localhost:5432/orderdb";
    private static final String USER ="postgres";
    private static final String PASS ="2004Gabi%";

    private static final ConnnectionFactory instance=new ConnnectionFactory();
    /**
     * Constructor privat care incarca driverul bazei de date.
     */
    private ConnnectionFactory(){
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(){
        Connection connection=null;
        try {
            connection=DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Returneaza o conexiune activa catre baza de date.
     *
     * @return Obiectul Connection operational
     */
    public static Connection getConnection(){
        return instance.createConnection();
    }
    /**
     * Inchide in siguranta conexiunea primita ca parametru, daca aceasta este deschisa.
     *
     * @param connection Conexiunea care trebuie inchisa
     */
    public static void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
