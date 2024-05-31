package com.promineotech;

import java.util.Random;

public class TestDemo {
	public int addPositive(int a, int b) {
		if(a>0 && b>0) {
			return a+b;
		}
		throw new IllegalArgumentException();
	}
	
	//Here is my own test method
	public int ownTestMethod(int a, int b, int c) {
		//if the product of a and b is greater than c, it returns product
		if((a*b)>c) {
			return (a*b);
		}
		throw new IllegalArgumentException();
	}
	
	public int randomNumberSquared() {
		int r = getRandomInt();
		return r*r;
	}
	public int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) +1;
	}
}
