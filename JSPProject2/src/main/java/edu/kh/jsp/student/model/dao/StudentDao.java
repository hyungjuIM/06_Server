package edu.kh.jsp.student.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jsp.common.JDBCTemplate.*;
import edu.kh.jsp.student.model.vo.Student;

public class StudentDao {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	private Statement stmt2;
	private PreparedStatement pstmt2;
	private ResultSet rs2;
	private Properties prop2;

	//기본 생성자
	public StudentDao() {
		try {
			String filePath = StudentDao.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
			String filePath2 = StudentDao.class.getResource("/edu/kh/jsp/sql/student-sql.xml").getPath();
			
			prop = new Properties();
			prop2 = new Properties();
			
			prop.loadFromXML(new FileInputStream(filePath));
			prop2.loadFromXML(new FileInputStream(filePath2));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/** 학생 전체조회 DAO
	 * @param conn
	 * @return
	 */
	public List<Student> selectAll(Connection conn) throws Exception {
		
		//결과 저장용 변수 선언
		List<Student> stdList = new ArrayList<>();
		try {
			//SQL 작성하기
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			
			
			//sql 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				String studentNo = rs.getString("STUDENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				stdList.add(
				new Student(studentNo, studentName, studentAddress, departmentName)
				);
			}
		}finally {
			//사용한 객체 자원 반환
			close(rs);
			close(stmt);
		}
		return stdList;
	}
	
	
	
public List<Student> selectdp(Connection conn2) throws Exception {
		
		//결과 저장용 변수 선언
		List<Student> stdList2 = new ArrayList<>();
		try {
			//SQL 작성하기
			String sql2 = prop.getProperty("selectdp");
			
			// Statement 객체 생성
			stmt2 = conn2.createStatement();
			
			
			
			//sql 수행 후 결과(ResultSet) 반환 받기
			rs2 = stmt2.executeQuery(sql2);
			
			
			
			while(rs2.next()) {
				String studentNo = rs2.getString("STUDENT_NO");
				String studentName = rs2.getString("STUDENT_NAME");
				String studentAddress = rs2.getString("STUDENT_ADDRESS");
				String departmentName = rs2.getString("DEPARTMENT_NAME");
				stdList2.add(
				new Student(studentNo, studentName, studentAddress, departmentName)
				);
			}
		}finally {
			//사용한 객체 자원 반환
			close(rs2);
			close(stmt2);
		}
		return stdList2;
	}


}
