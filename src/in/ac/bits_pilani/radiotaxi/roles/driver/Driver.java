package in.ac.bits_pilani.radiotaxi.roles.driver;

import in.ac.bits_pilani.radiotaxi.roles.User;

public class Driver extends User {

	private String licenseNo;
	private String carNo;
	
	public Driver(String username, String firstname, String lastname, String mobileNo,String licenseNo,String carNo, int balance) {
		super(username, firstname, lastname, mobileNo,balance);
		this.licenseNo=licenseNo;
		this.carNo=carNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public String getCarNo() {
		return carNo;
	}

}
