package com.kh.action.person.model.vo;

public class Person {

	private String name;
	private char gender;
	private int age;
	private boolean married;
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public boolean isMarried() {
		return married;
	}


	public void setMarried(boolean married) {
		this.married = married;
	}


	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + ", married=" + married + "]";
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name, char gender, int age, boolean married) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.married = married;
	}

}
