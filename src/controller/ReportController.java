package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import connection.connectReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import model.Report;

import javafx.scene.text.Font;




public class ReportController extends Controller implements Initializable{

    @FXML
    private ImageView HelloImage;

    @FXML
    private Label HelloName;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPre;

    @FXML
    private Button btnSend;

    @FXML
    private Button buttonHome;

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
    private VBox report;

    @FXML
    private TextField textReport;


    private int currentPage = 1;
    private int totalPage ; // số truy report / 5

    @FXML
    private void previous(ActionEvent event) {
        if (currentPage > 1) {
            currentPage--;
            display(currentPage);
        }
    }

    @FXML
    private void next(ActionEvent event) {
        if (currentPage < totalPage/5) {
            currentPage++;
            display(currentPage);
        }
    }

    private void display(int page) {
    	List<Report>reports=connectReport.readReportEmployee();
    	String []content=new String[reports.size()];
    	LocalTime []time=new LocalTime[reports.size()];
    	LocalDate []date=new LocalDate[reports.size()];
    	String []nameE=new String[reports.size()];
    	for(int i=0;i<reports.size();i++) {
    		content[i]=reports.get(i).getContent();  	
    		time[i]=reports.get(i).getTime();
    		date[i]=reports.get(i).getDate();
    		nameE[i]=connectReport.getName(reports.get(i).getReportID());
    	}
    	totalPage=reports.size();
        report.getChildren().clear();
        int start = (page - 1) * 5 + 1;
        double labelWidth = 800; 
        double labelHeight = 160; 
        int j=0;
        for (int i = start - 1; i < Math.min(start + 4, totalPage); i++) {
            if (i < content.length) {
                Label label = new Label("Comment " + (i + 1) +" ["+time[i]+" - "+date[i]+"] : "+ content[i]+" ( by : "+nameE[i]+" )");
                label.setAlignment(Pos.TOP_LEFT);
                label.setPrefWidth(labelWidth);
                label.setPrefHeight(labelHeight);
                label.setFont(new Font(16));
                label.setWrapText(true); 
                label.setStyle("-fx-padding: 5;");
                report.getChildren().add(label);
            }
        }

    }


    @FXML
    public void adminHome(ActionEvent event) throws IOException {
        super.adminHome(event);
    }

    @FXML
    public void adminEmployees(ActionEvent event) throws IOException {
        super.adminEmployees(event);
    }

    @FXML
    public void adminDepartment(ActionEvent event) throws IOException {
        super.adminDepartment(event);
    }


	 @FXML
	    public void logout(ActionEvent event)throws IOException {
	    	super.logout(event);
	 }
	 @FXML
	    public void sendReport(ActionEvent event)throws IOException {
		 LocalTime currentTime = LocalTime.now();
		 LocalDate currentDate = LocalDate.now();
		 if(!textReport.getText().isEmpty()) {
		 Report report=new Report(id,currentTime,currentDate,textReport.getText());
		 int n=connectReport.addReport(report);
		 if(n>0) {
			  System.out.println("Yes");
			  textReport.setText("");
		 }else {
			  System.out.println("No" );
		 }
		 }
	    }

    @FXML
    public void calendar(ActionEvent event) throws IOException {
        super.calendar(event);
    }

    @FXML
    public void adminReport(ActionEvent event) throws IOException {
        super.adminReport(event);
    }


    @FXML
    public void adminSetting(ActionEvent event) throws IOException {
        super.adminMyAccount(event);
    }

   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialize totalPage here based on the total number of reports
        //totalPage = getTotalPageCount(); // You need to implement this method
        display(currentPage);
        try {
			displayName();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
