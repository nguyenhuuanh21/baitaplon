package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import connection.connectDepartment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Department;
import model.Employee;


public class SettingController extends Controller implements Initializable {
	 @FXML
	 private DatePicker SettingBirth;

    @FXML
    private Label settingError;

    @FXML
    private TextField SettingPhone;
    
    @FXML
    private TextField settingAddress;

    @FXML
    private ComboBox<String> settingDepartment;

    @FXML
    private TextField settingEmail;

    @FXML
    private ComboBox<String> settingGender;

    @FXML
    private TextField settingName;

    @FXML
    private TextField settingPassword;

    @FXML
    private ImageView myImage;

    @FXML
    private Button settingSave;
    
    @FXML
    private Button changeImage;

	@FXML
    public void adminHome(ActionEvent event)throws IOException{
    	super.adminHome(event);
    }
	

	@FXML
    public void adminEmployees(ActionEvent event)throws IOException{
    	super.adminEmployees(event);
    }
	@FXML
    public void adminDepartment(ActionEvent event)throws IOException{
    	super.adminDepartment(event);
    }
	@FXML
    public void adminDelete(ActionEvent event)throws IOException{
    	super.adminDelete(event);
    }
	
	@FXML
    public void adminAdd(ActionEvent event)throws IOException{
    	super.adminAdd(event);
    }
	
	@FXML
    public void calendar(ActionEvent event)throws IOException{
    	super.calendar(event);
    }
	
	@FXML
    public void adminReport(ActionEvent event)throws IOException{
    	super.adminReport(event);
    }
	
	@FXML
    public void adminSetting(ActionEvent event)throws IOException{
    	super.adminMyAccount(event);
    }
	

	 @FXML
	    public void logout(ActionEvent event)throws IOException {
	    	super.logout(event);
	 }

	 public void insertImage() {
	     FileChooser fileChooser = new FileChooser();
	     fileChooser.setTitle("Select Image File");
	     fileChooser.getExtensionFilters().addAll(
	             new FileChooser.ExtensionFilter("image", "*.png", "*.jpg", "*.gif")
	     );

	     File selectedFile = fileChooser.showOpenDialog(new Stage());
	     if (selectedFile != null) {
	         try {
	             // Chuyển đổi đường dẫn tệp tin thành URL và sau đó lấy đường dẫn tuyệt đối từ URL
	             String imagePath = selectedFile.toURI().toURL().toExternalForm();
	             // Tạo hình ảnh từ đường dẫn tệp tin đã được chuyển đổi
	             Image image = new Image(imagePath);
	             // Hiển thị hình ảnh trong ImageView
	             myImage.setImage(image);
	             int result = ConnectEmployee.updateImageByIdAD(id, image);
		            if (result > 0) {
		                System.out.println("Image updated successfully.");
		            } else {
		                System.out.println("Failed to update image.");
		            }
	             // Tiếp tục xử lý...
	         } catch (Exception e) {
	             e.printStackTrace();
	             System.out.println("Failed to load image.");
	         }
	     }
	 }
	 
	 @FXML
	 public void saveInfor(ActionEvent event) {
		 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Save Profile");
         alert.setContentText("Do you want to save?");
         alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
         alert.showAndWait().ifPresent(response -> {
             if (response == ButtonType.OK) {
            	 Employee employee=new Employee(settingName.getText(),settingGender.getValue(),SettingBirth.getValue(),
            			 settingDepartment.getValue(),settingAddress.getText(),SettingPhone.getText(),settingEmail.getText(),settingPassword.getText());
            	 int n=ConnectEmployee.updateAdminById(id, employee);
            	 if(n!=0) {
            		
            		 connectDepartment.updateQuantityEmployee();
            		 System.out.println("yes");
            	 }else {
            		 System.out.println("no");
            	 }
            	 try {
					super.adminMyAccount(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
         });
	 }
	 @FXML
	    private void showGender() {
		  settingGender.getItems().add("Male");
		  settingGender.getItems().add("Female");
	    }
	  @FXML
	    private void showDepartment() {
	    	List<Department> departments = connectDepartment.readDepartment();
	    	String []id=new String[departments.size()+1];
	    	for(int i=0;i<departments.size();i++) {
	    		id[i]=departments.get(i).getDepartment_id();
	    		settingDepartment.getItems().addAll(id);
	    	}
	    }
	  public void display() throws SQLException, IOException {
		 	
			Employee em = ConnectEmployee.readAdById(id);			
			settingName.setText(em.getName());
			SettingPhone.setText(em.getPhone());
			settingPassword.setText(em.getPassword());
			settingEmail.setText(em.getEmail());
			settingDepartment.setValue(em.getDepartment());
			settingGender.setValue(em.getGender());
			SettingBirth.setValue(em.getBirth());
			settingAddress.setText(em.getAddress());
			myImage.setImage(em.getImage());
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			showGender();
			showDepartment();
			display();
			displayName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
