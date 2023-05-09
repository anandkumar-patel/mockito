package anand.learn.mockjunit.all;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {

	// @InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	MathApplication mathApplication = new MathApplication();

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	CalculatorService calcService;

	@Before
	public void instantiate() {
		// add the behavior of calc service to add two numbers
		when(calcService.add(10.0, 20.0)).thenReturn(30.00);

		// add the behavior of calc service to subtract two numbers
		when(calcService.subtract(20.0, 10.0)).thenReturn(10.00);

		// add the behavior of calc service to multiply two numbers
		when(calcService.multiply(6.0, 5.0)).thenReturn(30.00);

		// add the behavior of calc service to devide two number
		when(calcService.divide(10.0, 2.0)).thenReturn(5.0);

		// add the behavior of calc service to devide by zro throw exception
		when(calcService.divide(10.0, 0.0)).thenThrow(new ArithmeticException("devidde by zero"));
	}

	@Test
	public void testAddition() {
		// test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
		verify(calcService).add(10.00, 20.0);
	}

	@Test
	public void testSubtract() {

		// test the add functionality
		Assert.assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0);

		// verify the behavior
		verify(calcService).subtract(20.0, 10.0);
	}

	@Test
	public void testMultiplication() {

		// test the add functionality
		Assert.assertEquals(mathApplication.multiply(6.0, 5.0), 30.0, 0);
		Assert.assertEquals(mathApplication.multiply(6.0, 15.0), 90.0, 90.0);

		// verify the behavior
		verify(calcService).multiply(6.0, 5.0);
	}

	@Test
	public void testDevision() {

		mathApplication.divide(10.0, 2.0);

		// verify the behavior
		verify(calcService).divide(10.0, 2.0);

		// devide by zero exception
		// mathApplication.divide(10.0,0);
	}

	@Test
	public void testAll() {
		// test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

		// test the subtract functionality
		Assert.assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0.0);

		// default call count is 1
		verify(calcService).subtract(20.0, 10.0);

		// check if add function is called three times
		verify(calcService, times(3)).add(10.0, 20.0);

		// verify that method was never called on a mock
		verify(calcService, never()).multiply(10.0, 20.0);

		// check a minimum 1 call count
		verify(calcService, atLeastOnce()).subtract(20.0, 10.0);

		// check if add function is called minimum 2 times
		verify(calcService, atLeast(2)).add(10.0, 20.0);

		// check if add function is called maximum 3 times
		verify(calcService, atMost(3)).add(10.0, 20.0);
	}

	@Test
	public void testAddAndSubtractOrder() {

		// test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

		// test the subtract functionality
		Assert.assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0);

		// create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(calcService);

		// following will make sure that add is first called then subtract is
		// called.
		inOrder.verify(calcService).add(10.0, 20.0);
		inOrder.verify(calcService).subtract(20.0, 10.0);
	}

	@Test
	public void testCallbacks() {

		// add the behavior to add numbers
		when(calcService.add(10.0, 20.0)).thenAnswer(new Answer<Double>() {

			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				// get the arguments passed to mock
				Object[] args = invocation.getArguments();

				// get the mock
				Object mock = invocation.getMock();

				// return the result
				return 30.0;
			}
		});

		// test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
	}

	@Test
	public void testAddAndSubtractReset() {

		// test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

		// reset the mock
		//reset(calcService);

		// test the add functionality after resetting the mock
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
	}
	
	@Test
	public void whenCreateMock_thenCreated() {
		List mockedList = mock(ArrayList.class);

		mockedList.add("one");
		verify(mockedList).add("one");

		Assert.assertEquals(0, mockedList.size());
	}
	
	@Test
	public void whenCreateSpy_thenCreate() {
	    List spyList = spy(new ArrayList());
	    spyList.add("one");
	    verify(spyList).add("one");

	    Assert.assertEquals(1, spyList.size());
	}
	
	@Test
	public void whenStubASpy_thenStubbed() {
	List<String> list = new ArrayList<String>();
	List<String> spyList = spy(list);
	Assert.assertEquals(0, spyList.size());
	doReturn(100).when(spyList).size();
	Assert.assertEquals(100, spyList.size());
	}

}