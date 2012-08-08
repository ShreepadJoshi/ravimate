package com.validator;

public class UserInputValidator {

	public boolean validate(String[] userInput) throws InvalidInputException {
		if(userInput.length < 1){
			throw new InvalidInputException();			
		}
		return true;
	}

}
