<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
	prefix : 접두사
	uri(Uniform Resource Identifier) : 자원 구분 식별자(주소형태)
 --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>3. JSTL</title>
</head>
<body>
    <h1>JSTL(Jsp Standard Tag Library)</h1>
    <pre>
    	JSP에서 자주 사용되거나 공통적으로 사용되는 Java코드를
    	표기법을 간단히 태그화 하여 표준으로 제공하는 것
    	
    	(if, for, 배열/컬렉션 길이, 문자열 치환, 숫자 데이터 형식 변경,
    	데이터 파싱, scope 변수 선언 등)
    	
    	
    </pre>
    	<h3>JSTL 라이브러리 등록방법</h3>
    	<ol>
    		<li>https://tomcat.apache.org/download-taglibs.cgi 접속</li>
    		<li>Jar Files -> Impl. Spec, EL다운로드</li>
    		<li>webapp/WEB-INF/lib 폴더에 추가하기 </li>
    	</ol>
    	
    	<hr>
    	
    	<h3>JSTL 사용을 위한 선언 방법</h3>
    	<pre>
    		JSP 파일 최상단에 추가하고자 하는 JSTL taglib를 추가
    	</pre>
    	<hr>
    	<h1>1. 변수 선언 (c:set 태그)</h1>
    	<pre>
    		- 변수를 선언하고 값을 초기화 하는 태그
    			(원하는 scope의 내장 객체에 값 세팅)
    			
    		- c:set 속성
    		1) var : 변수명(setAttribute의 key값)
    		2) value : 대입될 값
    		3) scope : 내장 객체 범위(기본값 : page)
    	</pre>
    	<%-- scriptlet 작성법 --%>
    	<% pageContext.setAttribute("num1",10); %>
    	
    	<%-- JSTL 작성법 --%>
    	<c:set var="num2" value="20" />
    	
    	${num1} / ${num2}
    	
    	<%-- request scope에 num2세팅 --%>
    	<c:set var="num2" value="300" scope="request" />    	
    	<h4>${num2}</h4> <!-- 20 -->
    	<h4>${requestScope.num2}</h4> <!-- 300 -->
    	
    	<c:set var="temp" value="임시값" scope="session" />
    	<h4>temp : ${temp}</h4>
    	<hr>
    	
    	<h1>2. 변수 삭제(c:rempve)</h1>
    	<pre>
    		- c:set / setAttribute()로 추가된 값 제거
    		
    		- c:remove속성
    		1) var : 삭제할 변수명(key)
    		2) scope : 삭제할 내장 객체 범위 (기본값: 모든 범위)
    		</pre>
    		
    		<p>
    		<c:remove var="temp" />
    		temp : ${temp}<br>
    		
    		<c:set var="test" value="page" scope="page" />
    		<c:set var="test" value="request" scope="request" />
    		<c:set var="test" value="session" scope="session" />
    		<c:set var="test" value="application" scope="application" />
    		
    		<c:remove var="test" scope="request"/>
    		
    		${pageScope.test} , ${requestScope.test} , ${sessionScope.test} , ${applicationScope.test}
    		</p>
    	
    	
    	
</body>
</html>