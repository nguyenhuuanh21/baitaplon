package controllerE;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import connection.connectCheckEmployee;
import connection.connectReport;
import controllerE.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import model.CheckEmployee;
import model.Report;
public class Home extends controller implements Initializable {
	private int idCur;
	private LocalTime checkinTime = LocalTime.now();
	int year = LocalDate.now().getYear();
	private LocalDate currentDate = LocalDate.now();
    @FXML
    private Label Name;
    
    @FXML
    private Label label2;

    @FXML
    private Label label3;
    @FXML
    private Label label1;
    @FXML
    private LineChart<String, Number> LINECHART;
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
	 ZonedDateTime dateFocus = ZonedDateTime.now();
	    @FXML
	    private void inLineChart() {
	        
	    	LINECHART.setTitle("Working Time Per Month Of You");
	    	LINECHART.getXAxis().setLabel("Month");
	    	LINECHART.getYAxis().setLabel("Time(Hour)");
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	       
	       Map<Integer,Double> map = connectCheckEmployee.geTimeEmployee(id,dateFocus.getYear());
	       for(int i= 1 ;i <= dateFocus.getMonthValue();i++) {
	    	   String value = Integer.toString(i);
	    	   if(map.containsKey(i)) {
	    		   series.getData().add(new XYChart.Data<>(value, map.get(i)));
	    	   }else {
	    		   series.getData().add(new XYChart.Data<>(value,0));
	    	   }
	       }
	       LINECHART.getData().add(series);
	        if (LINECHART == null) {
	            System.out.println("lineChart is null");
	            return;
	        }
	        LINECHART.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
	        series.getNode().setStyle("-fx-stroke: gray");
	    }
	
	 @FXML
	 public void getCheckInTime(ActionEvent event)throws IOException {
	    // LocalTime checkinTime = LocalTime.now();
	//	 LocalDate currentDate = LocalDate.now();
		 if (!connectCheckEmployee.isAlreadyCheckedIn(id, currentDate)) {
			 CheckEmployee em=new CheckEmployee(id,checkinTime,currentDate);
			 int n=connectCheckEmployee.checkIn(em);
			 if(n>0) {
				 System.out.println("yes");
				// idCur=connectCheckEmployee.getIdCheck(em);
			 }else {
				 System.out.println("no");
			 }
		 }else {
			 System.out.println("False");
		 }
	 }
	
	 @FXML
	 public void getCheckOutTime(ActionEvent event)throws IOException  {		 
		 LocalTime checkoutTime=LocalTime.now();
		 CheckEmployee em=new CheckEmployee(id,checkinTime,currentDate);
		 idCur=connectCheckEmployee.getIdCheck(em);
		 int n=connectCheckEmployee.updateCheckId(idCur, checkoutTime);
		 if(n>0) {
			 System.out.println("yes");
		 }else {
			 System.out.println("no");
		 }
		 
	 }

	 public void setLatestReport() {
		 List<Report>list=connectReport.getReportLatest();
		 if(list.size()==1) {
			 label1.setText("( "+list.get(0).getDate()+" ) : "+list.get(0).getContent()  );
			 label2.setText("NULL");
			 label3.setText("NULL");
		 }
		 if(list.size()==2) {
			 label1.setText("( "+list.get(0).getDate()+" ) : "+list.get(0).getContent()  );
			 label2.setText("( "+list.get(1).getDate()+" ) : "+list.get(1).getContent()  );
			 label3.setText("NULL");
		 }
		 if(list.size()==3) {
			 label1.setText("( "+list.get(0).getDate()+" ) : "+list.get(0).getContent()  );
			 label2.setText("( "+list.get(1).getDate()+" ) : "+list.get(1).getContent()  );
			 label3.setText("( "+list.get(2).getDate()+" ) : "+list.get(2).getContent()  );
		 }
	 }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Name.setText(getName);
		setLatestReport();
		inLineChart();
		
	}

}
