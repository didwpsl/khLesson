package com.kh.design.pattern.strategy;
/*
 * strategy 클래스 
 */
public class NService implements CommonService{
	
	@Override
	public void test() {
		System.out.println("NService - test!!!");
	}
}
