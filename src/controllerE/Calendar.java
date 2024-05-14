package controllerE;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;


import connection.connectCheckEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
public class Calendar extends controller  implements Initializable  {
	
	ZonedDateTime dateFocus;
    ZonedDateTime today;
    
    @FXML
    private Label calendar_month;

    @FXML
    private Label calendar_year;

    @FXML
    private FlowPane calendar_date;

    @FXML
    private Label hi;
    @FXML
    public void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar_date.getChildren().clear();
        drawCalendar();
    }

    @FXML
    public void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar_date.getChildren().clear();
        drawCalendar();
    }
    
    
    public void drawCalendar() {
    	calendar_month.setText(String.valueOf(dateFocus.getYear()));
    	calendar_year.setText(String.valueOf(dateFocus.getMonth()));
    	double calendarWidth = calendar_date.getPrefWidth();
        double calendarHeight = calendar_date.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar_date.getHgap();
        double spacingV = calendar_date.getVgap();
        
        Map<Integer,Integer> map = connectCheckEmployee.getEmployeeAttendance(id, dateFocus.getMonthValue());
        int monthMaxDate = dateFocus.getMonth().maxLength();
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
        for(int i = 0 ;i < 6;i++) {
        	for(int j = 0 ;j < 7;j++) {
        		StackPane stackPane = new StackPane();
        		Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.GRAY);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);
                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        if(map.containsKey(currentDate)){
                            VBox calendarActivityBox = new VBox();
                            calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
                            calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
                            calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
                            Integer value = map.get(currentDate); 
                            if(value == 1) {
                            	 calendarActivityBox.setStyle("-fx-background-color:LIME");
                            }else if(value == 2) {
                            	calendarActivityBox.setStyle("-fx-background-color:ORANGE");
                            }else {
                            	calendarActivityBox.setStyle("-fx-background-color:ORANGERED");
                            }
                            stackPane.getChildren().add(calendarActivityBox);
                        }
                        
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar_date.getChildren().add(stackPane);
        	}
        }
    }

    private List<Integer> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int randomDay = random.nextInt(27) + 1;
            list.add(randomDay);
        }

        return list;
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
	 public void displayName() {
	    	hi.setText("Hello : " +getName );
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayName();
		// TODO Auto-generated method stub
		dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
	}
}
