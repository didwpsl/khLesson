package com.kh.design.pattern.strategy;
/*
 * strategy 클래스 
 */
public class DService implements CommonService{
	
	@Override
	public void test() {
		System.out.println("DService - test!!!");
	}
}
