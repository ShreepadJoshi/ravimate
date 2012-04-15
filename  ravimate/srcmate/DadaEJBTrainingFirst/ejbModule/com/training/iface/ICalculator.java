package com.training.iface;

import javax.ejb.Remote;

@Remote
public interface ICalculator {
	public double add(double i, double j);
	public double sub(double i, double j);
}
