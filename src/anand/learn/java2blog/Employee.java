package anand.learn.java2blog;

public class Employee {
	 
	int id;
	String name;
	Address address;
	
	
	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
 
	public String getEmployeeDetails()
	{
		return id+"_"+name+"_"+address.getAddressDetails();
	}
 
	public int getEmployeePinCode()
	{
		System.out.println("Pin code : "+address.getPinCode());
		return address.getPinCode();
	}
	
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Address getAddress() {
		return address;
	}
 
	public void setAddress(Address address) {
		this.address = address;
	}	
}