package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import connection.ConnectEmployee;
import connection.connectCheckEmployee;
import connection.connectDepartment;
import connection.connectReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Department;
import model.Employee;
import model.Report;

public class AdminHomeController extends Controller implements Initializable{
		@FXML
	    private ImageView HelloImage;
		@FXML
		private Label HelloName;
	    @FXML
	    LineChart<String, Number> myLineChart = new LineChart<>(new CategoryAxis(), new NumberAxis());
	    
	    @FXML
	    private PieChart myPieChart;
	    @FXML
	    private AnchorPane accountPane;
	    @FXML
	    private Label top1;

	    @FXML
	    private Label top2;

	    @FXML
	    private Label top3;
	    @FXML
	    private Label e1;

	    @FXML
	    private Label e2;

	    @FXML
	    private Label e3;
	    @FXML
	    private Label labelD;

	    @FXML
	    private Label labelE;
	    @FXML
	    private Label labelT;	
	    ZonedDateTime dateFocus = ZonedDateTime.now();
	    @FXML
	    private void inLineChart() {
	        
	    	myLineChart.setTitle("Average Working Time Per Employee");
	    	myLineChart.getXAxis().setLabel("Month");
	    	myLineChart.getYAxis().setLabel("Time(Hour)");
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	       
	       Map<Integer,Double> map = connectCheckEmployee.getAVGTimeEmployee(dateFocus.getYear());
	       for(int i= 1 ;i <= dateFocus.getMonthValue();i++) {
	    	   String value = Integer.toString(i);
	    	   if(map.containsKey(i)) {
	    		   series.getData().add(new XYChart.Data<>(value, map.get(i)));
	    	   }else {
	    		   series.getData().add(new XYChart.Data<>(value,0));
	    	   }
	       }
	        myLineChart.getData().add(series);
	        if (myLineChart == null) {
	            System.out.println("lineChart is null");
	            return;
	        }
	        myLineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
	        series.getNode().setStyle("-fx-stroke: gray");
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
		 public void display() throws SQLException, IOException {
		    	HelloName.setText("Hello : " +name );
		    	Employee em=ConnectEmployee.readAdById(id);
		    	HelloImage.setImage(em.getImage());
		    	List<Department>list=connectDepartment.readDepartment();
		    	int amt=list.size();
		    	labelD.setText(String.valueOf(amt));
		    	List<Employee>listE=ConnectEmployee.readEmployee();
		    	int amtE=listE.size();
		    	labelE.setText(String.valueOf(amtE));
		    	int amtT=connectCheckEmployee.getCountToday(LocalDate.now());
		    	labelT.setText(String.valueOf(amtT));
		    }
		 private void inPieChart() {
    	 List<Department> list = connectDepartment.readDepartment();
    	    ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList();
    	    
    	    // Duyệt qua danh sách phòng ban và thêm dữ liệu vào PieChart
    	    for (Department department : list) {
    	        pieCharData.add(new PieChart.Data(department.getDepartment_name(), department.getDpartment_quanity()));
    	    }
		myPieChart.setData(pieCharData);
		if(myPieChart == null) {
			System.out.println("Pie chart is null");
		}
	}
    
    
  
		 public void setLatestReport() {
			 List<Report>list=connectReport.getReportLatestEmployee();
			 System.out.println(list.get(0).toString());
			 if(list.size()==1) {
				 top1.setText("( "+list.get(0).getDate()+" ) : "+list.get(0).getContent()  );
				 top2.setText("NULL");
				 top3.setText("NULL");
			 }
			 if(list.size()==2) {
				 top1.setText("( "+list.get(0).getDate()+" ) : "+list.get(0).getContent()  );
				 top2.setText("( "+list.get(1).getDate()+" ) : "+list.get(1).getContent()  );
				 top3.setText("NULL");
			 }
			 if(list.size()==3) {
				 top1.setText("( "+list.get(0).getDate()+" ) : "+list.get(0).getContent()  );
				 top2.setText("( "+list.get(1).getDate()+" ) : "+list.get(1).getContent()  );
				 top3.setText("( "+list.get(2).getDate()+" ) : "+list.get(2).getContent()  );
			 }
		 }
		 public void setTopEmployee() {
			 List<Employee>list=connectCheckEmployee.getTopEmployee();
			 if(list.size()==1) {
				 e1.setText("TOP 1. "+list.get(0).getName());
				 e2.setText("TOP 2. NULL");
				 e3.setText("TOP 3. NULL");
			 }
			 if(list.size()==2) {
				 e1.setText("TOP 1. "+list.get(0).getName());
				 e2.setText("TOP 2. "+list.get(1).getName());
				 e3.setText("TOP 3. NULL");
			 }
			 if(list.size()==3) {
				 e1.setText("TOP 1. "+list.get(0).getName());
				 e2.setText("TOP 2. "+list.get(1).getName());
				 e3.setText("TOP 3. "+list.get(2).getName());
			 }
		 }
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			display();
			displayName();
			setLatestReport();
			setTopEmployee();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		inLineChart();
		inPieChart();
	}
}
