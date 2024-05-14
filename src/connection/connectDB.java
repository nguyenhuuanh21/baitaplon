package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class connectDB {



    
    public static Connection getConnection() {
        var server = "LAPTOP-0M51BIGT\\SQLEXPRESS";
        var user = "sa";
        var password = "anhkk123";
        var db = "employee_management";
        var port = 1433;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(user);
        ds.setPassword(password);
        ds.setDatabaseName(db);
        ds.setServerName(server);
        ds.setPortNumber(port);
        ds.setEncrypt(false);
        Connection conn = null;
        try {
            conn = ds.getConnection();
            System.out.println("Kết nối với SQL Server thành công!");
            System.out.println(conn.getCatalog());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public enum ConnectionType {
        MYSQL,
        SQL_SERVER
    }
}