package com.training.bean;
import javax.ejb.Stateless;

import com.training.iface.ICalculator;

@Stateless(mappedName="calc")
public class Calculator implements ICalculator {

	@Override
	public double add(double i, double j) {
		// TODO Auto-generated method stub
		return i+j;
	}

	@Override
	public double sub(double i, double j) {
		// TODO Auto-generated method stub
		return i-j;
	}

}
