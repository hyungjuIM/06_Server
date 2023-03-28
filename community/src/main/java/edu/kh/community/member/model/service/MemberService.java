package edu.kh.community.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	public Member selectOne(String memberEmail) throws SQLException {
		Connection conn = getConnection();
		
		Member member=dao.selectOne(conn, memberEmail);
		close(conn);
		return member;
	}
	
	/** 로그인 서비스
	 * @param mem
	 * @return
	 * @throws Exception 
	 */
	public Member login(Member mem) throws Exception {
		// Connection 얻어오기
		Connection conn = getConnection();
		
		// DAO 수행
		Member loginMember = dao.login(conn, mem);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginMember;
	}

	/** 이메일 중복검사 Service
	 * @param memberEmail
	 * @return
	 * @throws Exception 
	 */
	public int emailDupCheck(String memberEmail) throws Exception {
		//커넥션 생성
		Connection conn = getConnection();
		
		//dao에 전달
		int result =dao.emailDupCheck(conn,memberEmail);
		
		close(conn);
		
		return result;
	}

	/** 인증번호 DB추가 service
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 * @throws Exception 
	 */
	public int insertCertification(String inputEmail, String cNumber) throws Exception {
		Connection conn = getConnection();
		// 1) 입력한 이메일과 일치하는 값이 있으면 수정(UPDATE)
		int result = dao.updateCertification(conn, inputEmail, cNumber);
		// 2) 일치하는 이메일이 없는 경우 -> 처음으로 인증번호를 발급받음 -> 삽입(insert)
		if(result ==0) {
			result = dao.insertCertification(conn, inputEmail, cNumber);
			
		}
		// 트랜잭션 제어(DML일때)
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	

}
