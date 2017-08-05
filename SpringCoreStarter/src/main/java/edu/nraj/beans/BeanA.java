package edu.nraj.beans;

public class BeanA {
	private String name;
	
	public BeanA(String name) {
		this.name = name;
	}
	
	public void getName(){
		System.out.println("Name is: " + name);
	}
}
