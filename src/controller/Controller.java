package controller;
import java.io.IOException;
import java.sql.SQLException;

import connection.ConnectEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
public class Controller {
	private Stage stage;
	private AnchorPane root;
	private Scene scene;
	
	@FXML
    private ImageView HelloImage;

    @FXML
    private Label HelloName;
	
	protected int id = LoginController.getID();
	protected String name = LoginController.getName();

	@FXML
    public void adminHome(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
    		 System.out.println(id);
				root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
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
	public void displayName() throws SQLException, IOException {
    	HelloName.setText("Hello : " +name );
    	Employee em=ConnectEmployee.readAdById(id);
    	HelloImage.setImage(em.getImage());
    }
	@FXML
    public void setting(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/Setting.fxml"));
			} catch (IOException e) {
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
    public void adminEmployees(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/ListEmployees.fxml"));
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
    public void adminDepartment(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/ListDepartment.fxml"));
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
    public void adminDelete(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/DeleteAccount.fxml"));
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
    public void adminAdd(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/AddAccount.fxml"));
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
    public void calendar(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/Calendar.fxml"));
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
    public void adminReport(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/Report.fxml"));
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
    public void adminMyAccount(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/view/MyAccount.fxml"));
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
	    public void logout(ActionEvent event)throws IOException {
	    	 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	         alert.setTitle("Logout");
	         alert.setContentText("Do you want to logout?");
	         alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
	         alert.showAndWait().ifPresent(response -> {
	             if (response == ButtonType.OK) {
	            	 stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
	                 try {
						root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                 scene = new Scene(root);
	                 stage.setScene(scene);
	                 stage.show();
	             }
	         });
	 }

	
}
