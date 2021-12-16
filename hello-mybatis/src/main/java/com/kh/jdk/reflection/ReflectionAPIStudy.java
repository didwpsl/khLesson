package com.kh.jdk.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
 * 클래스 객체를 통해 클래스 정보를 분석하고 
 * 객체 생성/메소드 호출/필드값 활용 등이 가능한 프로그래밍 기법
 */

public class ReflectionAPIStudy {
	//throws Exception 으로 수정 
	public static void main(String[] args) throws Exception {
		ReflectionAPIStudy study = new ReflectionAPIStudy();
		study.test1();
		study.test2();
	}
	/**
	 * 객체 생성
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException
	 */
	private void test2() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Class객체는 제네릭이므로 <Sample>생략 가능 <?> 로 작성 
		Class<?> clz = Sample.class;
		
		//생성자
		Constructor<?>[] constructors = clz.getDeclaredConstructors();
		for(Constructor<?> constr : constructors) {
			System.out.println(constr);
		}
		
		//newInstance는 Object를 리턴하므로 형변환 필요 
		Sample inst1 = (Sample)constructors[0].newInstance(null);
		System.out.println(inst1);
		
		Sample inst2 = (Sample)constructors[1].newInstance(100, "안녕");
		System.out.println(inst2);
		
	}
	
	/*
	 * 클래스 객체 가져오기 
	 * @throws ClassNotFoundException
	 */
	//new 연산자 없이 새로운 객체 만들기 
	private void test1() throws ClassNotFoundException {
		Class clz1 = Sample.class;
		Class clz2 = Class.forName("com.kh.jdk.reflection.Sample");
		
		System.out.println(clz1);
		System.out.println(clz2);
		
		System.out.println(clz1 == clz2);
	}
}
