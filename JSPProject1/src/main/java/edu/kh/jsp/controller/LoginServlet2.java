package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		String id = req.getParameter("inputId");
//		String[] pw = req.getParameterValues("inputPw");
//
//		String name = req.getParameter("inputName");
//		String email = req.getParameter("inputEmail");
//		String[] hobby = req.getParameterValues("inputhobby");
//		
//		
//		String result = null;
//		if(id.equals(id)&&pw.equals(pw)&&name.equals(name)&&email.equals(email)&&hobby.equals(hobby)){
//			 
//			result= name+"의 회원가입이 완료되었습니다.";
//		}else {
//			result="회원가입에 실패했습니다.";
//		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginEX.jsp");
		//req.setAttribute("r", result);
		dispatcher.forward(req, resp);
	}

}
