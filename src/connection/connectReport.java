package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Report;

public class connectReport {
	public static int addReport(Report report) {
		var conn=connectDB.getConnection();
		try(conn) {
			String sql="INSERT INTO report(admin_id, report_time, report_date, report_content)"
             		+ " values(?,?,?,?)";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setInt(1,report.getEmployeeID());
             LocalTime localtime = report.getTime();
             java.sql.Time sqlTime= java.sql.Time.valueOf(localtime);
             ps.setTime(2, (java.sql.Time) sqlTime);
             LocalDate localDate = report.getDate();
             java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
             ps.setDate(3, (java.sql.Date) sqlDate);
             ps.setString(4,report.getContent());
             return ps.executeUpdate();
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        } 
	}
	public static int addReportEmployee(Report report) {
		var conn=connectDB.getConnection();
		try(conn) {
			String sql="INSERT INTO reportE(employee_id, report_time, report_date, report_content)"
             		+ " values(?,?,?,?)";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setInt(1,report.getEmployeeID());
             LocalTime localtime = report.getTime();
             java.sql.Time sqlTime= java.sql.Time.valueOf(localtime);
             ps.setTime(2, (java.sql.Time) sqlTime);
             LocalDate localDate = report.getDate();
             java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
             ps.setDate(3, (java.sql.Date) sqlDate);
             ps.setString(4,report.getContent());
             return ps.executeUpdate();
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        } 
	}
	public static List<Report> readReport() {
        List<Report> reports = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT * FROM report ORDER BY report_id ASC "; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
                var report_id = resultSet.getInt(1);
                var employee_id = resultSet.getInt(2);
                var report_time=resultSet.getTime(3).toLocalTime();
                var report_date=resultSet.getDate(4).toLocalDate();
                var report_content=resultSet.getString(5);
                Report report = new Report(report_id,employee_id,report_time,report_date,report_content);
                reports.add(report); 
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } 
        return reports;
    }
	public static List<Report> getReportLatest() {
        List<Report> reports = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT TOP 3  * FROM report ORDER BY report_id DESC "; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
                var report_id = resultSet.getInt(1);
                var employee_id = resultSet.getInt(2);
                var report_time=resultSet.getTime(3).toLocalTime();
                var report_date=resultSet.getDate(4).toLocalDate();
                var report_content=resultSet.getString(5);
                Report report = new Report(report_id,employee_id,report_time,report_date,report_content);
                reports.add(report); 
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } 
        return reports;
    }
	public static List<Report> readReportEmployee() {
        List<Report> reports = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT * FROM reportE ORDER BY report_id ASC "; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
                var report_id = resultSet.getInt(1);
                var employee_id = resultSet.getInt(2);
                var report_time=resultSet.getTime(3).toLocalTime();
                var report_date=resultSet.getDate(4).toLocalDate();
                var report_content=resultSet.getString(5);
                Report report = new Report(report_id,employee_id,report_time,report_date,report_content);
                reports.add(report); 
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } 
        return reports;
    }
	public static List<Report> getReportLatestEmployee() {
        List<Report> reports = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT TOP 3 * FROM reportE ORDER BY report_id DESC "; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
                var report_id = resultSet.getInt(1);
                var employee_id = resultSet.getInt(2);
                var report_time=resultSet.getTime(3).toLocalTime();
                var report_date=resultSet.getDate(4).toLocalDate();
                var report_content=resultSet.getString(5);
                Report report = new Report(report_id,employee_id,report_time,report_date,report_content);
                reports.add(report); 
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } 
        return reports;
    }
	public static String getName(int id) {
	    Connection conn = connectDB.getConnection();
	    String name = "";
	    try (conn) {
	        String sql = "SELECT e.employee_name " +
	                     "FROM Employee e JOIN reportE r ON e.employee_id = r.employee_id " +
	                     "WHERE r.report_id = ?"; // câu lệnh truy vấn SQL
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        var resultSet = ps.executeQuery();
	        if (resultSet.next()) { // chỉ lấy hàng đầu tiên (nếu có)
	            name = resultSet.getString("employee_name"); // lấy giá trị của cột "employee_name"
	        }
	    } catch (SQLException throwables) {
	        throwables.printStackTrace();
	    }
	    return name;
	}
	

}
