package controllerE;
import java.io.IOException;

import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controller {
	private Stage stage;
	private AnchorPane root;
	private Scene scene;
	protected int id = LoginController.getID();
	protected String getName = LoginController.getName();
	@FXML
    public void edit(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/viewE/Setting.fxml"));
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
    public void HomeE(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/viewE/Home.fxml"));
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
    public void settingE(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/viewE/Setting.fxml"));
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
    public void calendarE(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/viewE/Calendar.fxml"));
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
    public void notificationE(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/viewE/Notification.fxml"));
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
    public void MyAccountE(ActionEvent event)throws IOException{
    	stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	 try {
				root = FXMLLoader.load(getClass().getResource("/viewE/MyAccount.fxml"));
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
