package test.com.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.validator.InvalidInputException;
import com.validator.UserInputValidator;

public class UserInputValidatorTest {

	@Test
	public void ValidInputTest() {
		UserInputValidator inputValidator = new UserInputValidator();
		String[] UserInput = new String[] { "search", "1123222312" };
		boolean result = inputValidator.validate(UserInput);
		assertTrue(result);
	}
		
	@Test
	public void invalidInputExceptionTest() {
		UserInputValidator inputValidator = new UserInputValidator();
		String[] UserInput = new String[] {};
		try{
			boolean result = inputValidator.validate(UserInput);
			fail("should throw InvalidInputException");
					 
		}catch(InvalidInputException invalidInputException){
			System.out.println(invalidInputException);
			assertTrue(invalidInputException instanceof InvalidInputException);
		}
		
		
	}
	
}
