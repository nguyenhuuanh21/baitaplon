package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Report {
	@Override
	public String toString() {
		return "Report [reportID=" + reportID + ", EmployeeID=" + EmployeeID + ", time=" + time + ", date=" + date
				+ ", content=" + content + "]";
	}
	int reportID;
	int EmployeeID;
	LocalTime time;
	LocalDate date;
	String content;
	public Report(int employeeID, LocalTime time, LocalDate date,String content) {
		super();		
		EmployeeID = employeeID;
		this.time = time;
		this.date = date;
		this.content=content;
	}
	
	public Report(int reportID, int employeeID, LocalTime time, LocalDate date, String content) {
		super();
		this.reportID = reportID;
		EmployeeID = employeeID;
		this.time = time;
		this.date = date;
		this.content = content;
	}

	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
