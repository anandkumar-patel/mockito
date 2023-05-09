package anand.learn.mockjunit.spying;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {
	
   private MathApplication mathApplication;
   private CalculatorService calcService;

   @Before
   public void setUp(){
      mathApplication = new MathApplication();
      Calculator calculator = new Calculator();
      calcService = spy(calculator);
      mathApplication.setCalculatorService(calcService);	     
   }

   @Test
   public void testAddition(){

      //perform operation on real object
      //test the add functionality
      Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
   }
   
   @Test
   public void testSubtract(){

      //perform operation on real object
      //test the subtract functionality
      Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0);
   }
   
   @Test
	public void testMultiplication() {

		// test the add functionality
		Assert.assertEquals(mathApplication.multiply(6.0, 5.0), 30.0, 0);
		Assert.assertEquals(mathApplication.multiply(6.0, 15.0), 90.0, 90.0);


		// verify the behavior
		verify(calcService).multiply(6.0,5.0);
	}
	
	@Test
	public void testDevision() {

		mathApplication.divide(10.0, 2.0);

		// verify the behavior
		verify(calcService).divide(10.0,2.0);
		
		//devide by zero exception
		//mathApplication.divide(10.0,0);
	}
}