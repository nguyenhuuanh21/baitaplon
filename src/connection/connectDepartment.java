package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import model.Department;

public class connectDepartment {
	 public static List<Department> readDepartment() {
	        List<Department> departments = new ArrayList<>();		       
	        Connection conn =connectDB.getConnection();
	        try (conn) {
	            var sql = "SELECT * FROM dbo.departments ORDER BY department_id ASC "; // câu lệnh truy vấn SQL
	            var statement = conn.createStatement(); // lấy đối tượng Statement
	            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
	            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	                var department_id = resultSet.getString(1);
	                var department_name = resultSet.getString(2);
	                var department_quantity=resultSet.getInt(3);
	                Department department = new Department(department_id,department_name,department_quantity);
	                departments.add(department); 
	            }
	        } catch (SQLServerException throwables) {
	            throwables.printStackTrace();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	        return departments;
	    }
	 public static void updateQuantityEmployee() {
			Connection conn =connectDB.getConnection();
			try (conn) {
	            var sql = "UPDATE departments\r\n"
	            		+ "SET department_quantity = (\r\n"
	            		+ "    SELECT COUNT(*)\r\n"
	            		+ "    FROM (\r\n"
	            		+ "        SELECT department_id FROM Employee\r\n"
	            		+ "        UNION ALL\r\n"
	            		+ "        SELECT department_id FROM Admin\r\n"
	            		+ "    ) AS combined\r\n"
	            		+ "    WHERE combined.department_id = departments.department_id\r\n"
	            		+ ")"; // câu lệnh truy vấn SQL
	            var statement = conn.createStatement(); // lấy đối tượng Statement
	            var resultSet = statement.executeUpdate(sql); // lấy đối tượng ResultSet
	        } catch (SQLServerException throwables) {
	            throwables.printStackTrace();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
			
		}
	 public static List<Department> findDepartmentByName(String name) {
		 Connection conn =connectDB.getConnection();
		 List<Department> departments = new ArrayList<>();	
	     try (conn) {
	            var sql = "SELECT * FROM dbo.departments WHERE department_name LIKE ?"; // câu lệnh truy vấn SQL
	            PreparedStatement ps=conn.prepareCall(sql);
	            ps.setString(1, "%" + name + "%");
	            var resultSet = ps.executeQuery(); // lấy đối tượng ResultSet
	            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	                var department_id = resultSet.getString(1);
	                var department_name = resultSet.getString(2);
	                var department_quantity=resultSet.getInt(3);
	                Department department = new Department(department_id,department_name,department_quantity);
	                departments.add(department);
	            }
	        } catch (SQLServerException throwables) {
	            throwables.printStackTrace();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
		return departments;
		 
	 }

}


/*
import connection.ConnectDB.ConnectionType;
import model.Department;

public class connectDepartment {

    public static List<Department> readDepartment() throws SQLException {
        List<Department> departments = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Get connection from ConnectDB class
            conn = ConnectDB.getConnection(ConnectionType.MYSQL);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");

            while (resultSet.next()) {
                String department_id = resultSet.getString("department_id");
                String department_name = resultSet.getString("department_name");
                Department department = new Department(department_id, department_name);
                departments.add(department);
            }
        } finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return departments;
    }
}
*/
