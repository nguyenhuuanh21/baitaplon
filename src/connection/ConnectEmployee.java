package connection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.Department;
import model.Employee;

public class ConnectEmployee {
	 public static List<Employee> findEmployeeByName(String name) {
		 Connection conn =connectDB.getConnection();
		 List<Employee> employees = new ArrayList<>();	
	     try (conn) {
	            var sql = "SELECT * FROM dbo.Employee WHERE employee_name LIKE ?"; // câu lệnh truy vấn SQL
	            PreparedStatement ps=conn.prepareCall(sql);
	            ps.setString(1, "%" + name + "%");
	            var resultSet = ps.executeQuery(); // lấy đối tượng ResultSet
	            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	            	var employee_id = String.valueOf(resultSet.getInt(1));             
	                var employee_name = resultSet.getString(2);
	                var gender=resultSet.getString(3);
	                var date_of_birth_sql=resultSet.getDate(4);
	                var date_of_birth=date_of_birth_sql.toLocalDate();
	                var department_id=resultSet.getString(5);
	                var address=resultSet.getString(6);
	                var phone=resultSet.getString(7);
	                var email=resultSet.getString(8);
	                var password=resultSet.getString(9);
	                Employee employee = new Employee(employee_id,employee_name,gender,date_of_birth,department_id,address,phone,email,password);
	                employees.add(employee); 
	            }
	        } catch (SQLServerException throwables) {
	            throwables.printStackTrace();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
		return employees;
		 
	 }
	public static int addEmployee(Employee employee) {
		Connection conn=connectDB.getConnection();
		int n = 0;
		try(conn){
			 String sql="INSERT INTO Employee(employee_name, gender, date_of_birth, department_id, address, phone, email, password)"
             		+ " values(?,?,?,?,?,?,?,?)";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setString(1,employee.getName());
             ps.setString(2,employee.getGender());
             LocalDate localDate = employee.getBirth();
             java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
             ps.setDate(3, (java.sql.Date) sqlDate);
             ps.setString(4,employee.getDepartment());
             ps.setString(5,employee.getAddress());
             ps.setString(6,employee.getPhone());
             ps.setString(7,employee.getEmail());
             ps.setString(8,employee.getPassword());
             n=ps.executeUpdate();	
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		 return n;
	}
	public static boolean getAccount(Employee employee) {
		Connection conn=connectDB.getConnection();
		ResultSet rs;
		try(conn){
			String sql="select*from Employee where email=? and password=?";
			PreparedStatement ps=conn.prepareCall(sql);
			ps.setString(1, employee.getEmail());
	        ps.setString(2, employee.getPassword());
			rs=ps.executeQuery();
			if( rs.next()) {
				return true;
			}
		}catch(SQLException e)  {
			e.printStackTrace();
		}
		
			return false;
	}


    /*
    public static String getName(int id) throws SQLException {
        String name = null;
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        ResultSet rs;
        try (conn) {
            String sql = "SELECT employee_name FROM Employee WHERE employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("employee_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    /*
    public static int getId(Employee employee) throws SQLException {
        Connection conn = ConnectDB.getConnection(ConnectionType.MYSQL);
        ResultSet rs;
        int id = -1;
        try (conn) {
            String sql = "select * from Employee where email=? and password = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, employee.getEmail());
            ps.setString(2, employee.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("employee_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    


	}
	*/
	public static List<Employee> readEmployee() {
        List<Employee> employees = new ArrayList<>();		       
        Connection conn =connectDB.getConnection();
        try (conn) {
            var sql = "SELECT * FROM dbo.Employee"; // câu lệnh truy vấn SQL
            var statement = conn.createStatement(); // lấy đối tượng Statement
            var resultSet = statement.executeQuery(sql); // lấy đối tượng ResultSet
            while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
            	var employee_id = String.valueOf(resultSet.getInt(1));             
                var employee_name = resultSet.getString(2);
                var gender=resultSet.getString(3);
                var date_of_birth_sql=resultSet.getDate(4);
                var date_of_birth=date_of_birth_sql.toLocalDate();
                var department_id=resultSet.getString(5);
                var address=resultSet.getString(6);
                var phone=resultSet.getString(7);
                var email=resultSet.getString(8);
                var password=resultSet.getString(9);
                Employee employee = new Employee(employee_id,employee_name,gender,date_of_birth,department_id,address,phone,email,password);
                employees.add(employee); 
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }
	public static int getIdByAccount(Employee employee) {
		int id=0;
		var conn=connectDB.getConnection();
		try(conn){
			String sql="select * from Employee where email=? and password=?";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setString(1,employee.getEmail());
             ps.setString(2,employee.getPassword());
	         var resultSet = ps.executeQuery();
	         while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	             id=resultSet.getInt("employee_id");
	         }
		}catch(SQLException e)  {
			e.printStackTrace();
		}
		return id;
	}
	public static String getNameByAccount(Employee employee) {
		String name="";
		var conn=connectDB.getConnection();
		try(conn){
			String sql="select * from Employee where email=? and password=?";
			 PreparedStatement ps=conn.prepareCall(sql);
             ps.setString(1,employee.getEmail());
             ps.setString(2,employee.getPassword());
	         var resultSet = ps.executeQuery();
	         while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
	             name=resultSet.getString("employee_name");
	         }
		}catch(SQLException e)  {
			e.printStackTrace();
		}
		return name;
	}
	 public static Employee readEmployeeById(int id) throws SQLException, IOException {
	        Employee employee = null;
	        Connection conn = connectDB.getConnection();
	        try (conn) {
	            var sql = "SELECT * FROM Employee where employee_id = ?" ;
	            var ps = conn.prepareStatement(sql);
	            ps.setInt(1, id);
	            var resultSet=ps.executeQuery();
	            while (resultSet.next()) {
	                var employee_id = String.valueOf(resultSet.getInt(1));
	                var employee_name = resultSet.getString(2);
	                var gender = resultSet.getString(3);
	                var date_of_birth_sql = resultSet.getDate(4);
	                var date_of_birth = date_of_birth_sql.toLocalDate();
	                var department_id = resultSet.getString(5);
	                var address = resultSet.getString(6);
	                var phone = resultSet.getString(7);
	                var email = resultSet.getString(8);
	                var password = resultSet.getString(9);
	                byte[] imageData = resultSet.getBytes("image");
	                Image image = null; // Khởi tạo image là null mặc định
	                if (imageData != null) {
	                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
	                    image = SwingFXUtils.toFXImage(bufferedImage, null);
	                } else {
	                	 //image = getDefaultImage();
	                }
	                employee = new Employee(employee_id, employee_name, gender, date_of_birth, department_id,
	                        address, phone, email, password, image);
	                
	                
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
	        return employee;
	    }
	 /*
	 private static Image getDefaultImage() {
		   String defaultImagePath = "C:\\Users\\Admin\\Pictures\\vector-users-icon.png"; // Đường dẫn đến hình ảnh mặc định
		    Image defaultImage = new Image(new File(defaultImagePath).toURI().toString());
		    return defaultImage;
		
	}
	*/
	public static int updateEmployeeById(int id,Employee employee) {
		 int n=0;
		 Connection conn = connectDB.getConnection();
		 try(conn){
			 var sql="UPDATE Employee SET employee_name = ? , gender = ? , date_of_birth = ? , department_id = ? , address = ? , phone = ? , email = ? , password = ? WHERE employee_id = ?";
			 var ps = conn.prepareStatement(sql);
			 ps.setString(1, employee.getName());
			 ps.setString(2, employee.getGender());
			 LocalDate localDate = employee.getBirth();
	         java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
	         ps.setDate(3, (java.sql.Date) sqlDate);
	         ps.setString(4, employee.getDepartment());
	         ps.setString(5, employee.getAddress());
	         ps.setString(6,employee.getPhone());
	         ps.setString(7,employee.getEmail());
	         ps.setString(8,employee.getPassword());
	         ps.setInt(9,id);
	         return ps.executeUpdate();
		 }catch (SQLServerException ex) {
	           	ex.printStackTrace();
	            return -1;
	        } catch (SQLException ex) {
	        	 ex.printStackTrace();
	            return -1;
	        }
	 }
	 public static byte[] imageToByteArray(Image image) {
		    try {
		        // Chuyển đổi Image thành BufferedImage
		        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

		        // Tạo một ByteArrayOutputStream để ghi dữ liệu ảnh vào
		        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		        // Ghi BufferedImage vào ByteArrayOutputStream
		        ImageIO.write(bufferedImage, "png", outputStream);

		        // Trả về mảng byte chứa dữ liệu ảnh
		        return outputStream.toByteArray();
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	 
	 public static int updateImageById(int id, Image image) {
		    Connection conn = connectDB.getConnection();
		    int n = 0;
		    try (conn) {
		    	 byte[] imageData=imageToByteArray(image);
		        var sql = "UPDATE Employee SET image = ? WHERE employee_id = ?";
		        var ps = conn.prepareStatement(sql);
		        ps.setBytes(1, imageData);
		        ps.setInt(2, id);
		        return ps.executeUpdate();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return -1;
		    }
		}
	 public static int deleteEmployeeById(int id) {
		 Connection conn = connectDB.getConnection();
		 int n=0;
		 try (conn) {
	        var sql = "DELETE rg\r\n"
	        		+ "FROM \r\n"
	        		+ "    checkE rg\r\n"
	        		+ "    INNER JOIN Employee st\r\n"
	        		+ "    ON rg.employee_id = st.employee_id\r\n"
	        		+ "WHERE\r\n"
	        		+ "    st.employee_id = ?\r\n"
	        		+ "\r\n"
	        		+ "DELETE rk\r\n"
	        		+ "FROM \r\n"
	        		+ "    reportE rk\r\n"
	        		+ "    INNER JOIN Employee st\r\n"
	        		+ "    ON rk.employee_id = st.employee_id\r\n"
	        		+ "WHERE\r\n"
	        		+ "    st.employee_id = ?\r\n"
	        		+ "DELETE FROM Employee\r\n"
	        		+ "WHERE\r\n"
	        		+ "    employee_id = ?";
	        var ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        ps.setInt(2, id);
	        ps.setInt(3, id);
	        return ps.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return -1;
	    }
	 }
	 //CHO ADMIN
	 public static int updateImageByIdAD(int id, Image image) {
		    Connection conn = connectDB.getConnection();
		    int n = 0;
		    try (conn) {
		    	 byte[] imageData=imageToByteArray(image);
		        var sql = "UPDATE Admin SET image = ? WHERE admin_id = ?";
		        var ps = conn.prepareStatement(sql);
		        ps.setBytes(1, imageData);
		        ps.setInt(2, id);
		        return ps.executeUpdate();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return -1;
		    }
		}
		public static boolean getAccountAD(Employee employee) {
			Connection conn=connectDB.getConnection();
			ResultSet rs;
			try(conn){
				String sql="select*from Admin where email=? and password=?";
				PreparedStatement ps=conn.prepareCall(sql);
				ps.setString(1, employee.getEmail());
		        ps.setString(2, employee.getPassword());
				rs=ps.executeQuery();
				if( rs.next()) {
					return true;
				}
			}catch(SQLException e)  {
				e.printStackTrace();
			}
			
				return false;
		}
		public static int getIdByAccountAD(Employee employee) {
			int id=0;
			var conn=connectDB.getConnection();
			try(conn){
				String sql="select * from Admin where email=? and password=?";
				 PreparedStatement ps=conn.prepareCall(sql);
	             ps.setString(1,employee.getEmail());
	             ps.setString(2,employee.getPassword());
		         var resultSet = ps.executeQuery();
		         while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
		             id=resultSet.getInt("admin_id");
		         }
			}catch(SQLException e)  {
				e.printStackTrace();
			}
			return id;
		}
		public static String getNameByAccountAD(Employee employee) {
			String name="";
			var conn=connectDB.getConnection();
			try(conn){
				String sql="select * from Admin where email=? and password=?";
				 PreparedStatement ps=conn.prepareCall(sql);
	             ps.setString(1,employee.getEmail());
	             ps.setString(2,employee.getPassword());
		         var resultSet = ps.executeQuery();
		         while (resultSet.next()) { // nếu có hàng dữ liệu kế tiếp
		             name=resultSet.getString("admin_name");
		         }
			}catch(SQLException e)  {
				e.printStackTrace();
			}
			return name;
		}
		 public static Employee readAdById(int id) throws SQLException, IOException {
		        Employee employee = null;
		        Connection conn = connectDB.getConnection();
		        try (conn) {
		            var sql = "SELECT * FROM Admin where admin_id = ?" ;
		            var ps = conn.prepareStatement(sql);
		            ps.setInt(1, id);
		            var resultSet=ps.executeQuery();
		            while (resultSet.next()) {
		                var admin_id = String.valueOf(resultSet.getInt(1));
		                var admin_name = resultSet.getString(2);
		                var gender = resultSet.getString(3);
		                var date_of_birth_sql = resultSet.getDate(4);
		                var date_of_birth = date_of_birth_sql.toLocalDate();
		                var department_id = resultSet.getString(5);
		                var address = resultSet.getString(6);
		                var phone = resultSet.getString(7);
		                var email = resultSet.getString(8);
		                var password = resultSet.getString(9);
		                
		                byte[] imageData = resultSet.getBytes("image");
		                Image image = null; // Khởi tạo image là null mặc định
		                if (imageData != null) {
		                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
		                    image = SwingFXUtils.toFXImage(bufferedImage, null);
		                } else {
		                    // Nếu không có dữ liệu hình ảnh từ cơ sở dữ liệu, bạn có thể xác định hình ảnh mặc định ở đây
		                    // Ví dụ:
		                    // image = getDefaultImage();
		                	 //image = getDefaultImage();
		                }
		                employee = new Employee(admin_id, admin_name, gender, date_of_birth, department_id,
		                        address, phone, email, password, image);
		                
		                
		            }
		        } catch (SQLException throwables) {
		            throwables.printStackTrace();
		        }
		        return employee;
		    }
		 public static int updateAdminById(int id,Employee employee) {
			 Connection conn = connectDB.getConnection();
			 try(conn){
				 var sql="UPDATE Admin SET admin_name = ? , gender = ? , date_of_birth = ? , department_id = ? , address = ? , phone = ? , email = ? , password = ? WHERE admin_id = ?";
				 var ps = conn.prepareStatement(sql);
				 ps.setString(1, employee.getName());
				 ps.setString(2, employee.getGender());
				 LocalDate localDate = employee.getBirth();
		         java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		         ps.setDate(3, (java.sql.Date) sqlDate);
		         ps.setString(4, employee.getDepartment());
		         ps.setString(5, employee.getAddress());
		         ps.setString(6,employee.getPhone());
		         ps.setString(7,employee.getEmail());
		         ps.setString(8,employee.getPassword());
		         ps.setInt(9,id);
		         return ps.executeUpdate();
			 }catch (SQLServerException ex) {
		           	ex.printStackTrace();
		            return -1;
		        } catch (SQLException ex) {
		        	 ex.printStackTrace();
		            return -1;
		        }catch(Exception e) {
		        	e.printStackTrace();
		        	return -1;
		        }
		 }
}
