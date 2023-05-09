package anand.learn.temp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	List<String> mockedList;
	
	@Test
	public void whenUseMockAnnotation_thenMockIsInjected() {
		mockedList.add("one");
		System.out.println("Arraylist size :"+mockedList.size()+" element :"+mockedList.get(0));
		Mockito.verify(mockedList).add("one");
		assertEquals(0, mockedList.size());

		Mockito.when(mockedList.size()).thenReturn(100);
		assertEquals(100, mockedList.size());
	}
	
	@Spy
	List<String> spiedList = new ArrayList<String>();
	 
	@Test
	public void whenUseSpyAnnotation_thenSpyIsInjected() {
	    spiedList.add("one");
	    spiedList.add("two");
		
	    System.out.println("Arraylist size :"+spiedList.size()+" element :"+spiedList.get(0));

	    Mockito.verify(spiedList).add("one");
	    Mockito.verify(spiedList).add("two");
	 
	    assertEquals(2, spiedList.size());
	 
	    Mockito.doReturn(100).when(spiedList).size();
	    assertEquals(100, spiedList.size());
	}
	
	
}
