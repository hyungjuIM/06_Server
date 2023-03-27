package edu.kh.community.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.member.model.service.UserService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/selectAll")
public class SelectAjaxServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			// 서비스 객체 생성
			UserService service = new UserService();

			// 회원 전체 조회 서비스 호출, 결과 반환
			List<Member> memList = service.selectAll();

			new Gson().toJson(memList, resp.getWriter());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
