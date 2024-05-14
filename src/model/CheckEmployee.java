package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckEmployee {
	private int check_Id;
	private int employee_Id;
	LocalTime checkin;
	LocalTime checkout;
	LocalDate date;
	public CheckEmployee( int employee_Id, LocalDate date) {
		super();
		this.employee_Id = employee_Id;
		this.date = date;
	}
	public CheckEmployee( int employee_Id, LocalTime checkin, LocalDate date) {
		super();
		this.employee_Id = employee_Id;
		this.checkin = checkin;
		this.date = date;
	}
	public CheckEmployee(int check_Id, int employee_Id, LocalTime checkin, LocalDate date) {
		super();
		this.check_Id = check_Id;
		this.employee_Id = employee_Id;
		this.checkin = checkin;
		this.date = date;
	}
	public int getCheck_Id() {
		return check_Id;
	}
	public void setCheck_Id(int check_Id) {
		this.check_Id = check_Id;
	}
	public int getEmployee_Id() {
		return employee_Id;
	}
	public void setEmployee_Id(int employee_Id) {
		this.employee_Id = employee_Id;
	}
	public LocalTime getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalTime checkin) {
		this.checkin = checkin;
	}
	public LocalTime getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalTime checkout) {
		this.checkout = checkout;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
