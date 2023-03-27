package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.member.model.vo.Member;

public class UserDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public UserDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Member> selectAll(Connection conn) throws SQLException {

		List<Member> memList = new ArrayList<>();
		Member member = null;

		try {
			String sql = prop.getProperty("selectAll");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new Member();

				member.setMemberNo(rs.getInt(1));
				member.setMemberEmail(rs.getString(2));
				member.setMemberNickname(rs.getString(3));

				memList.add(member);

			}

		} finally {
			close(rs);
			close(pstmt);

		}
		return memList;
	}

}
