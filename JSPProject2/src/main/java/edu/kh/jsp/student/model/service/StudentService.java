package edu.kh.jsp.student.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.jsp.common.JDBCTemplate.*;

import edu.kh.jsp.student.model.dao.StudentDao;
import edu.kh.jsp.student.model.vo.Student;

public class StudentService {
	private StudentDao dao = new StudentDao();
public List<Student> selectAll() throws Exception{
	Connection conn = getConnection();
	
	// dao 메서드 호출 후 결과 반환 받기
	List<Student> stdList = dao.selectAll(conn);
	
	//커넥션 객체 반환
	close(conn);
	
	return stdList;
}


public List<Student> selectDepartment(String inputDept) throws Exception{
	
	Connection conn = getConnection();
	
	List<Student> stdList = dao.selectDepartment(conn, inputDept);
	
	close(conn);
	
	return stdList;
}
}
