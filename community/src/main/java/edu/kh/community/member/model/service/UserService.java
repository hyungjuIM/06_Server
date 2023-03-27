package edu.kh.community.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.community.member.model.dao.UserDAO;
import edu.kh.community.member.model.vo.Member;
import static edu.kh.community.common.JDBCTemplate.*;

public class UserService {
	private UserDAO dao = new UserDAO();

	public List<Member> selectAll() throws SQLException {
		Connection conn = getConnection();

		List<Member> memList = dao.selectAll(conn);
		close(conn);

		return memList;
	}

}
