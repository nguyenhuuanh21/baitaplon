package model;


import java.time.LocalDate;

import javafx.scene.image.Image;

public class Employee {
	
	String id;
	String name;
	String gender;
	LocalDate birth;
	String department;
	String address;
	String phone;
	String email;
	String password;
	Image image;
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Employee(String id, String name, String gender, LocalDate birth, String department, String address,
			String phone, String email, String password, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.department = department;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.image = image;
	}
	
	public Employee(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Employee(String name, String gender, LocalDate birth, String department, String address, String phone,
			String email, String password) {
		super();
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.department = department;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}
	public Employee(String id,String name, String gender, LocalDate dateOfBirth, String department, String address, String phone,
			String email, String password) {
		super();
		this.id=id;
		this.name = name;
		this.gender = gender;
		this.birth = dateOfBirth;
		this.department = department;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Employee(String id, String name,String gender) {
		super();
		this.id = id;
		this.name = name;
		this.gender=gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", birth=" + birth + ", department="
				+ department + ", address=" + address + ", phone=" + phone + ", email=" + email + ", password="
				+ password + "]";
	}
	
}
