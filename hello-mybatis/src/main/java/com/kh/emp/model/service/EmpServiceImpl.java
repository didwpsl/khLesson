package com.kh.emp.model.service;

import static com.kh.common.SqlSessionTemplate.*;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.emp.model.dao.EmpDao;

public class EmpServiceImpl implements EmpService {

	private EmpDao empDao;

	public EmpServiceImpl(EmpDao empDao) {
		super();
		this.empDao = empDao;
	}

	@Override
	public List<Map<String, Object>> selectEmpMapList() {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.selectMapList(session);
		session.close();
		return list;
	}

	@Override
			//empVo String:컬럼명 Object:값
	public List<Map<String, Object>> search1(Map<String, Object> param) {
		SqlSession session = getSqlSession();
		List<Map<String, Object>> list = empDao.search1(session, param);
		session.close();
		return list;
	}

}
