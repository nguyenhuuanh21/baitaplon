package connection;
import model.CheckEmployee;
import model.Employee;
import model.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class connectCheckEmployee {
	public static int checkIn(CheckEmployee check) {
		Connection conn =connectDB.getConnection();
		try(conn){
			 String sql="INSERT INTO checkE(employee_id, checkin_time,checkE_date)"
	             		+ " values(?,?,?)";
			 PreparedStatement ps=conn.prepareCall(sql);
			 ps.setInt(1,check.getEmployee_Id());
			 LocalTime checkin = check.getCheckin();
			 java.sql.Time checkinTime= java.sql.Time.valueOf(checkin);
			 ps.setTime(2,(java.sql.Time) checkinTime);
			 LocalDate localDate = check.getDate();
             java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
             ps.setDate(3, (java.sql.Date) sqlDate);
             return ps.executeUpdate();
		}catch (SQLServerException throwables) {
            throwables.printStackTrace();
            return -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
	}
	public static int getIdCheck(CheckEmployee check) {
	    int n = 0;
	    Connection conn = connectDB.getConnection();
	    try (conn) {
	        String sql = "SELECT checkE_id FROM checkE WHERE employee_id = ?  AND checkE_date = ?";
	        PreparedStatement ps = conn.prepareCall(sql);
	        ps.setInt(1, check.getEmployee_Id());
	        LocalDate localDate = check.getDate();
	        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
	        ps.setDate(2, sqlDate);
	        var resultSet = ps.executeQuery();
	        while (resultSet.next()) {
	            n = resultSet.getInt("checkE_id");
	        }
	    } catch (SQLServerException throwables) {
	        throwables.printStackTrace();
	        return -1;
	    } catch (SQLException throwables) {
	        throwables.printStackTrace();
	        return -1;
	    }
	    return n;
	}

	public static int updateCheckId(int id, LocalTime checkoutTime) {
		 Connection conn = connectDB.getConnection();
		 try(conn){
			 var sql="UPDATE checkE SET checkout_time = ? WHERE checkE_id = ?";
			 var ps = conn.prepareStatement(sql);
			 java.sql.Time time= java.sql.Time.valueOf(checkoutTime);
			 ps.setTime(1,(java.sql.Time) time);
			 ps.setInt(2, id);
	         return ps.executeUpdate();
		 }catch (SQLServerException ex) {
	           	ex.printStackTrace();
	            return -1;
	        } catch (SQLException ex) {
	        	 ex.printStackTrace();
	            return -1;
	        }
	 }
	 public static boolean isAlreadyCheckedIn(int employeeId, LocalDate date) {
	        Connection conn = connectDB.getConnection();
	        try (conn) {
	            String sql = "SELECT COUNT(*) AS count FROM checkE WHERE employee_id = ? AND checkE_date = ?";
	            PreparedStatement ps = conn.prepareCall(sql);
	            ps.setInt(1, employeeId);
	            ps.setDate(2, java.sql.Date.valueOf(date));
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt("count");
	                return count > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 public static Map<Integer, Integer> getEmployeeAttendance(int id, int month) {
		    Map<Integer, Integer> attendanceMap = new HashMap<>();
		    String sql = "SELECT DAY(checkE_date) AS day_of_month,\r\n"
		    		+ "    CASE \r\n"
		    		+ "        WHEN COUNT(*) > 0 AND SUM(CASE WHEN checkout_time IS NULL OR checkin_time IS NULL THEN 1 ELSE 0 END) > 0 THEN 3\r\n"
		    		+ "        WHEN COUNT(*) > 0 AND SUM(DATEDIFF(HOUR, checkin_time, checkout_time)) >= 8 THEN 1\r\n"
		    		+ "        ELSE 2\r\n"
		    		+ "    END AS attendance_status\r\n"
		    		+ "FROM checkE\r\n"
		    		+ "WHERE MONTH(checkE_date) = ? AND employee_id = ?\r\n"
		    		+ "GROUP BY DAY(checkE_date);\r\n"
		    		+ "";

		    try (Connection conn = connectDB.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, month);
		        stmt.setInt(2, id); // Add employee_id parameter
		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                int dayOfMonth = rs.getInt("day_of_month");
		                int attendanceStatus = rs.getInt("attendance_status");
		                attendanceMap.put(dayOfMonth, attendanceStatus);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return attendanceMap;
		}
	 public static Map<Integer, Integer> totalEmployeeWork(int month) {
		    Map<Integer, Integer> employeeWorkMap = new HashMap<>();
		    String sql = "SELECT DAY(checkE_date) AS work_date, COUNT(DISTINCT employee_id) AS employee_count\r\n"
		    		+ "FROM checkE\r\n"
		    		+ "WHERE MONTH(checkE_date) = ?\r\n"
		    		+ "GROUP BY DAY(checkE_date);\r\n"
		    		+ "";

		    try (Connection conn = connectDB.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, month);
		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                Integer workDate = rs.getInt("work_date");
		                int employeeCount = rs.getInt("employee_count");
		                employeeWorkMap.put(workDate, employeeCount);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return employeeWorkMap;
		}
	 public static List<Employee> getTopEmployee() {
	        List<Employee> employees = new ArrayList<>();		       
	        Connection conn =connectDB.getConnection();
	        try (conn) {
	            var sql = "SELECT TOP 3 e.employee_id, e.employee_name, \r\n"
	            		+ "    SUM(DATEDIFF(MINUTE, ce.checkin_time, ce.checkout_time)) AS total_hours_worked\r\n"
	            		+ "FROM Employee e\r\n"
	            		+ "JOIN checkE ce ON e.employee_id = ce.employee_id\r\n"
	            		+ "GROUP BY e.employee_id, e.employee_name\r\n"
	            		+ "ORDER BY total_hours_worked DESC"; // câu lệnh truy vấn SQL
	            var statement = conn.createStatement(); // lấy đối tượng Statement
	            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
	            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	                var employee_id = String.valueOf(resultSet.getInt("employee_id"));  ;
	                var employee_name = resultSet.getString("employee_name");	                
	                Employee employee = new Employee(employee_id,employee_name,"");
	                employees.add(employee); 
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        } 
	        return employees;
	    }
	 public static Map<Integer, Double> geTimeEmployee(int id,int year) {
		    Map<Integer, Double> map = new HashMap<>();
		    String sql = "SELECT MONTH(checkE_date) AS month_of_year, \r\n"
		    		+ "    ROUND(SUM(CASE WHEN checkout_time IS NULL OR checkin_time IS NULL THEN 0 ELSE DATEDIFF(MINUTE, checkin_time, checkout_time) / 60.0 END), 2) AS total_hours_worked \r\n"
		    		+ "FROM checkE \r\n"
		    		+ "WHERE employee_id = ? AND YEAR(checkE_date) = ?"
		    		+ "GROUP BY MONTH(checkE_date);\r\n"
		    		+ "";

		    try (Connection conn = connectDB.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, id);
		        stmt.setInt(2, year);
		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                int monthOfYear = rs.getInt("month_of_year");
		                Double totalHoursWorked = rs.getDouble("total_hours_worked");
		                map.put(monthOfYear, totalHoursWorked);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return map;
		}
	 public static Map<Integer, Double> getAVGTimeEmployee(int year) {
		    Map<Integer, Double> map = new HashMap<>();
		    String sql = "SELECT \r\n"
		    		+ "    MONTH(checkE_date) AS month_of_year,\r\n"
		    		+ "    SUM(DATEDIFF(MINUTE, checkin_time, checkout_time) / 60.0) / COUNT(DISTINCT employee_id) AS avg_hours_worked_per_employee\r\n"
		    		+ "FROM \r\n"
		    		+ "    checkE\r\n"
		    		+ "WHERE\r\n"
		    		+ "    YEAR(checkE_date) = ?\r\n"
		    		+ "GROUP BY \r\n"
		    		+ "    MONTH(checkE_date);\r\n"
		    		+ "";

		    try (Connection conn = connectDB.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, year);

		        try (ResultSet rs = stmt.executeQuery()) {
		            while (rs.next()) {
		                int monthOfYear = rs.getInt("month_of_year");
		                Double totalHoursWorked = rs.getDouble("avg_hours_worked_per_employee");
		                map.put(monthOfYear, totalHoursWorked);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return map;
		}
	 public static int getCountToday(LocalDate date) {
		    int n = 0;
		    Connection conn = connectDB.getConnection();
		    try (conn) {
		        String sql = "SELECT COUNT(*) AS AMOUNT FROM checkE WHERE checkE_date = ?  ";
		        PreparedStatement ps = conn.prepareCall(sql);	       
		        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		        ps.setDate(1, sqlDate);
		        var resultSet = ps.executeQuery();
		        while (resultSet.next()) {
		            n = resultSet.getInt("AMOUNT");
		        }
		    } catch (SQLServerException throwables) {
		        throwables.printStackTrace();
		        return -1;
		    } catch (SQLException throwables) {
		        throwables.printStackTrace();
		        return -1;
		    }
		    return n;
		}

}
