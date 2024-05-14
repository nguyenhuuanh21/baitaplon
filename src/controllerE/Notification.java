package controllerE;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import connection.connectReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Report;
public class Notification extends controller implements Initializable {
	
	@FXML
	private VBox report;
	@FXML
	private int currentPage = 1;
	@FXML
	private int totalPage ;//số truy report / 5
    @FXML
    private Label hello;
    @FXML
    private Button btnEnter;
    @FXML
    private TextField textEnter;
	@FXML
	private void previous(ActionEvent event) {
		if(currentPage > 1) {
			currentPage--;
			display(currentPage);
		}
	}
	
	@FXML
	private void next(ActionEvent event) {
		if(currentPage < totalPage) {
			currentPage++;
			display(currentPage);
		}
	}
	
	 private void display(int page) {
	    	List<Report>reports=connectReport.readReport();
	    	String []content=new String[reports.size()];
	    	LocalTime []time=new LocalTime[reports.size()];
	    	LocalDate []date=new LocalDate[reports.size()];
	    	for(int i=0;i<reports.size();i++) {
	    		content[i]=reports.get(i).getContent();  
	    		time[i]=reports.get(i).getTime();
	    		date[i]=reports.get(i).getDate();
	    	}
	    	totalPage=reports.size();
	        report.getChildren().clear();
	        int start = (page - 1) * 5 + 1;
	        double labelWidth = 800; 
	        double labelHeight = 160; 
	        int j=0;
	        for (int i = start - 1; i < Math.min(start + 4, totalPage); i++) {
	            if (i < content.length) {
	            	 Label label = new Label("Comment " + (i + 1) +" ( "+time[i]+" - "+date[i]+" ) : "+ content[i]);
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
	void sendReport(ActionEvent event) throws IOException{
		LocalTime currentTime = LocalTime.now();
		LocalDate currentDate = LocalDate.now();
		if(!textEnter.getText().isEmpty()) {
		Report report=new Report(id,currentTime,currentDate,textEnter.getText());
		int n=connectReport.addReportEmployee(report);
		 if(n>0) {
			  System.out.println("Yes");
			  textEnter.setText("");
		 }else {
			  System.out.println("No" );
		 }
		}
	}
	 @FXML
	    public void logout(ActionEvent event)throws IOException {
	    	super.logout(event);
	 }
	 public void displayName() {
	    	hello.setText("Hello : " +getName );
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		display(currentPage);
		 displayName();
		
	}
}
