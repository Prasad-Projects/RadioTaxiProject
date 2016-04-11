package radiotaxi.roles.driver;

import radiotaxi.roles.User;

public class Driver extends User {

	private String license_no;
	private String car_no;
	
	public Driver(String username, String firstname, String lastname, String mobile_no,String license_no,String car_no, int balance) {
		super(username, firstname, lastname, mobile_no,balance);
		this.license_no=license_no;
		this.car_no=car_no;
	}

	public String getLicense_no() {
		return license_no;
	}

	public String getCar_no() {
		return car_no;
	}

}
