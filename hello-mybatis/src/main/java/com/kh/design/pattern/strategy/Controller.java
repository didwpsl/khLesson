package com.kh.design.pattern.strategy;

/*
 * context는 실행할 Strategy를 몰라야 한다 
 * 
 */
public class Controller {
	
	//Strategy가 보이면 고정이 되어버리므로 보이면 안됨 
	//CommonService 인터페이스 작성, 객체 생성 제거 
	//현재 상태로 실행시 null point
	//외부에서 주입 
	private CommonService service;
	
	public Controller(CommonService service) {
		this.service = service;
	}
	
	public void test() {
		service.test();
	}
}
