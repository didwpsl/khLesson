package com.kh.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public class StudentDao implements IStudentDao {

	@Override
	public int insertStudent(SqlSession session, Student student) {
		return session.insert("student.insertStudent", student);
	}
	@Override
	public int insertStudent(SqlSession session, Map<String, Object> studentMap) {
		return session.insert("student.insertStudentMap", studentMap);
	}
	@Override
	public int selectStudentTotalCount(SqlSession session) {
		return session.selectOne("student.selectStudentTotalCount");
	}
	@Override
	public Student selectOneStudent(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudent", no);
	}
	@Override
	public int updateStudent(SqlSession session, Student student) {
		return session.update("student.updateStudent", student);
	}
	@Override
	public int deleteStudent(SqlSession session, int no) {
		return session.delete("student.deleteStudent", no);
	}
	//Map을 사용한다고 해서 selectMap이 아님! 여전히 record는 하나! 학생 테이블에서 레코드 하나를 Map으로 담는 것 뿐 
	@Override
	public Map<String, Object> selectOneStudentMap(SqlSession session, int no) {
		return session.selectOne("student.selectOneStudentMap", no);
	}
	/*
	 * selectList는 조회된 행이 없는 경우 null이 아닌 빈 list 객체를 리턴한다 
	 */
	@Override
	public List<Student> selectStudentList(SqlSession session) {
		//여러행 일때는 selectList, 쿼리에 전달할 값 없으므로 두 번째 자리는 비워둔다 
		return session.selectList("student.selectStudentList");
	}
	@Override
	public List<Map<String, Object>> selectStudentMapList(SqlSession session) {
		return session.selectList("student.selectStudentMapList");
	}
	
}
