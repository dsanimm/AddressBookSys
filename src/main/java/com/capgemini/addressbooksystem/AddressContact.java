package com.capgemini.addressbooksystem;

public class AddressContact {
	public String firstName;
	public String lastName;
	public String city;
	public int zip;
	public long phoneNo;
	public String email;

	public AddressContact(String firstName, String lastName, String city, int zip, long phoneNo, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.zip = zip;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return firstName + "," + lastName + "," + city + "," + zip
				+ "," + phoneNo + "," + email + "\n";
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}