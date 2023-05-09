package anand.learn.mockjunit.spying;

import anand.learn.mockjunit.spying.MathApplicationTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(MathApplicationTest.class);
      
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      
      System.out.println(result.wasSuccessful());
   }
}  	