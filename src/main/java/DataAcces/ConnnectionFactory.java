package DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnnectionFactory {
    private static final String DRIVER ="org.postgresql.Driver";
    private static final String URL ="jdbc:postgresql://localhost:5432/postgres";
    private static final String USER ="postgres";
    private static final String PASS ="2004Gabi%";

    private static final ConnnectionFactory instance=new ConnnectionFactory();

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

    public static Connection getConnection(){
        return instance.createConnection();
    }

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
