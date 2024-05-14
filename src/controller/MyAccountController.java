/*
package controller;

import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
=======
import java.sql.SQLException;
>>>>>>> b6599124cc9e14729901aff54abd0b19b587e1f6
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
<<<<<<< HEAD


public class MyAccountController extends Controller  {
	private  Employee loggedInEmployee1;
	@FXML
    private Label SettingBirth;

    @FXML
    private Label SettingPhone;

    @FXML
    private Button menu_calendar;

    @FXML
    private Button menu_department;

    @FXML
    private Button menu_edit;

    @FXML
    private Button menu_logout;

    @FXML
    private Button menu_setting;

    @FXML
    private Label settingAddress;

    @FXML
    private Label settingDepartment;

    @FXML
    private Label settingEmail;

    @FXML
    private Label settingGender;

    @FXML
    private Label settingName;

    @FXML
    private Label settingPassword;


=======


public class MyAccountController extends Controller implements Initializable{
	
>>>>>>> b6599124cc9e14729901aff54abd0b19b587e1f6
	private Stage stage;
	private AnchorPane root;
	private Scene scene;
	
	@FXML
	private Label myID;
	
	
	 @FXML
	    private Label address;

	    @FXML
	    private Label birth;

	    @FXML
	    private Label department;

	    @FXML
	    private Label email;

	    @FXML
	    private Label gender;
	    @FXML
	    private Label name;

	    @FXML
	    private Label password;

	    @FXML
	    private Label phone;
	@FXML
	public void editProfile(ActionEvent event) {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
   	 try {
				root = FXMLLoader.load(getClass().getResource("/view/Setting.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       if(root == null) {
       	System.out.println("Root is null");
       	return;
       }
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();

	}
	
	@FXML
	public void display() throws SQLException {
		Employee em = ConnectEmployee.readEmployee(id);
		name.setText(em.getName());
		phone.setText(em.getPhone());
		password.setText(em.getPassword());
		email.setText(em.getEmail());
		department.setText(em.getDepartment());
		gender.setText(em.getGender());
		birth.setText(em.getBirth().toString());
		address.setText(em.getAddress());
		myID.setText("ID : " + id);
	}
	
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

<<<<<<< HEAD
}
*/
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;


public class MyAccountController extends Controller implements Initializable{
	@FXML
	private ImageView imageView;
	@FXML
    private Label SettingBirth;

    @FXML
    private Label SettingPhone;

    @FXML
    private Button menu_calendar;

    @FXML
    private Button menu_department;

    @FXML
    private Button menu_edit;

    @FXML
    private Button menu_logout;

    @FXML
    private Button menu_setting;

    @FXML
    private Label settingAddress;

    @FXML
    private Label settingDepartment;

    @FXML
    private Label settingEmail;

    @FXML
    private Label settingGender;

    @FXML
    private Label settingName;

    @FXML
    private Label settingPassword;
    @FXML
    private Label myID;

	private Stage stage;
	private AnchorPane root;
	private Scene scene;
	@FXML
	public void editProfile(ActionEvent event) {
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
   	 try {
				root = FXMLLoader.load(getClass().getResource("/view/Setting.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       if(root == null) {
       	System.out.println("Root is null");
       	return;
       }
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();

	}

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
	 @FXML
		public void display() throws SQLException,IOException {			 	
				Employee em = ConnectEmployee.readAdById(id);
				//System.out.print(em.toString());
				myID.setText( String.valueOf("ID : "+id));
				settingName.setText(em.getName());
				SettingPhone.setText(em.getPhone());
				settingPassword.setText(em.getPassword());
				settingEmail.setText(em.getEmail());
				settingDepartment.setText(em.getDepartment());
				settingGender.setText(em.getGender());
				SettingBirth.setText(em.getBirth().toString());
				settingAddress.setText(em.getAddress());
				imageView.setImage(em.getImage());								
			}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
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

