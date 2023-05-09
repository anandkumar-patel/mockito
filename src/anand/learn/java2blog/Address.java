package anand.learn.java2blog;

public class Address {
	 
	int houseNo;
	String street;
	int pinCode;
	
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		System.out.println("Setting pin code ");
		this.pinCode = pinCode;
	}
	public String getAddressDetails()
	{
		// let's say this is database operation
		return houseNo+"_"+street;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}	
}
