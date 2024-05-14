package controllerE;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Employee;
public class MyAccount extends controller implements Initializable {


	    @FXML
	    private Label HelloName;

	    @FXML
	    private Label address;

	    @FXML
	    private Label birth;
	    @FXML
	    private Label hello;
	    @FXML
	    private Label department;

	    @FXML
	    private Label email;

	    @FXML
	    private Label gender;



	    @FXML
	    private Button menu_calendar;

	    @FXML
	    private Button menu_logout;

	    @FXML
	    private Button menu_setting;

	    @FXML
	    private Label myID;

	    @FXML
	    private Label name;

	    @FXML
	    private Label password;

	    @FXML
	    private Label phone;
	    @FXML
	    private ImageView imageView;


	@FXML
    public void editProfile(ActionEvent event)throws IOException{
    	super.edit(event);
    }
	@FXML
    public void HomeE(ActionEvent event)throws IOException {
    	super.HomeE(event);
    }
	
	@FXML
    public void settingE(ActionEvent event)throws IOException{
    	super.settingE(event);
    }
	@FXML
    public void calendarE(ActionEvent event)throws IOException{
    	super.calendarE(event);
    }
	
	@FXML
    public void notificationE(ActionEvent event)throws IOException{
    	super.notificationE(event);
    }
	
	@FXML
    public void MyAccountE(ActionEvent event)throws IOException{
    	super.MyAccountE(event);
    }
	

	 @FXML
	    public void logout(ActionEvent event)throws IOException {
	    	super.logout(event);
	 }	
	 @FXML
		public void display() throws SQLException, IOException {
			 	
				Employee em = ConnectEmployee.readEmployeeById(id);
				myID.setText( String.valueOf("ID : "+id));
				name.setText(em.getName());
				phone.setText(em.getPhone());
				password.setText(em.getPassword());
				email.setText(em.getEmail());
				department.setText(em.getDepartment());
				gender.setText(em.getGender());
				birth.setText(em.getBirth().toString());
				address.setText(em.getAddress());
				imageView.setImage(em.getImage());
			}
	 public void displayName() {
	    	HelloName.setText("Hello : " +getName );
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


