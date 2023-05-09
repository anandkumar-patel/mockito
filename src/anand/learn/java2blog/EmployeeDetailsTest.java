package anand.learn.java2blog;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
 
import junit.framework.Assert;
 
public class EmployeeDetailsTest {
	
	Employee e1;
	Employee e2;
	
	@Spy
	Address address=new Address();
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Before
	public void setup(){
		e1=new Employee(1, "John");
		e1.setAddress(address);
		e2=new Employee(2, "Martin");
		address.setPinCode(222201);
		e2.setAddress(address);
		when(address.getAddressDetails()).thenReturn("88_HighStreet");
	}
	
	@Test
	public void testEmployeeDetails()
	{
		String e1Details=e1.getEmployeeDetails();
		Assert.assertEquals("1_John_88_HighStreet", e1Details);
		
		String e2Details=e2.getEmployeeDetails();
		Assert.assertEquals("2_Martin_88_HighStreet", e2Details);
		
		int e2PinDetails=e2.getEmployeePinCode();
		Assert.assertEquals(222201, e2PinDetails);
	}
}
