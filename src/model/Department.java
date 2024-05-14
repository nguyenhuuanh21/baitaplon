package model;

public class Department {
	private String department_id;
	private String department_name;
	private int dpartment_quanity;
	public Department() {
		super();
	}

	public Department(String department_id, String department_name) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public Department(String department_id, String department_name, int dpartment_quanity) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.dpartment_quanity = dpartment_quanity;
	}

	public int getDpartment_quanity() {
		return dpartment_quanity;
	}

	public void setDpartment_quanity(int dpartment_quanity) {
		this.dpartment_quanity = dpartment_quanity;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
}
