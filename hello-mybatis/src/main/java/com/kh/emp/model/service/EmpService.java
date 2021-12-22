package com.kh.emp.model.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	List<Map<String, Object>> selectEmpMapList();

	List<Map<String, Object>> search1(Map<String, Object> param);

}
