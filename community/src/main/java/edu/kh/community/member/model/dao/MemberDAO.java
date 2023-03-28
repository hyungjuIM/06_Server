package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.community.member.model.vo.Member;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, String memberEmail) throws SQLException {

		Member member = null;

		try {
			String sql = prop.getProperty("selectOne");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member();

				member.setMemberEmail(rs.getString(1));
				member.setMemberNickname(rs.getString(2));
				member.setMemberTel(rs.getString(3));
				member.setMemberAddress(rs.getString(4));
				member.setEnrollDate(rs.getString(5));
			}

		} finally {
			close(rs);
			close(pstmt);

		}
		return member;
	}

	/**
	 * 로그인 DAO
	 * 
	 * @param conn
	 * @param mem
	 * @return loginMember
	 */
	public Member login(Connection conn, Member mem) throws Exception {
		// 결과 저장용 변수 선언
		Member loginMember = null;

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("login");

			// PreparedStatement 생성 및 SQL 적재
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());

			// SQL 수행
			rs = pstmt.executeQuery();

			if (rs.next()) {

				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
				loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
				loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
				loginMember.setEnrollDate(rs.getString("ENROLL_DT"));
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		return loginMember; // 조회 결과 없으면 null, 있으면 Member객체 주소
	}

	/** email중복검사 dao
	 * @param conn
	 * @param memberEmail
	 * @return
	 */
	public int emailDupCheck(Connection conn, String memberEmail) throws Exception {
		
		// 결과 저장용 변수선언
		int result = 0;
		
		try {
			// sql얻어오기
			String sql = prop.getProperty("emailDupCheck");
			
			// pstmt 생성 -> 플레이스홀드 있으면 사용
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			
			// sql실행후 결과 반환받기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/** 인증번호 발급일 수정 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int updateCertification(Connection conn, String inputEmail, String cNumber) throws Exception{
		int result =0;
		try {
			String sql =prop.getProperty("updateCertification");
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, cNumber);
			pstmt.setString(2, inputEmail);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
		return result;
	}

	/** 인증번호 생성 DAO
	 * @param conn
	 * @param inputEmail
	 * @param cNumber
	 * @return
	 */
	public int insertCertification(Connection conn, String inputEmail, String cNumber) throws Exception {
		int result =0;
		try {
			String sql= prop.getProperty("insertCertification");
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, inputEmail);
			pstmt.setString(2, cNumber);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
