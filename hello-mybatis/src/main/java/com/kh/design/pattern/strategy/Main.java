package com.kh.design.pattern.strategy;

/*
 * Design Pattern 
 * -문제 해결을 위한 코드의 구조를 정리한 것 
 * -책 : GoF의 디자인 패턴(생성, 구조, 행위) 중 행위 패턴의 한 가지
 * 
 * -context가 전략(변경가능성이 있는 코드(strategy)을 사용함에 있어 interface규격을 통해 제어하는 것 
 * -context : strategy를 사용하는 쪽 
 * -interface : strategy의 규격 
 * -strategy(interface 구현 클래스)
 */

public class Main {

	public static void main(String[] args) {
		//파라미터로 객체를 넘겨준다 N서비스를 넘기면 N서비스,D서비스를 넘기면 D서비스 구현 
		Controller controller = new Controller(new KService());
		controller.test();
	}

}


