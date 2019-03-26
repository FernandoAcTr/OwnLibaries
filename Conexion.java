import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Connection connection;    
    private static final String database = "db_alumnos";
    private static final String user = "root";
    private static final String password = "fernando";    
    private static final String hostname = "localhosth";
    private static final String url = "jdbc:mysql://"+hostname+"/"+database;    

    public static Connection getConnection() {
        if(connection == null) Connect();
            return connection;
    }

    public static void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Initialized...");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResultSet runQuerySQL(String sentencia) throws SQLException {        
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery(sentencia);
        return resultado;
    }   

    public static void Disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isClosed(){
        try {
            return connection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }
}
