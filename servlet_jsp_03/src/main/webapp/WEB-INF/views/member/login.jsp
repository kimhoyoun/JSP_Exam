<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인 페이지</h1>
<!-- 
데이터가 어느 범위까지 남아있는지를 알고 사용하면 된다.
유효하는 스코프가 달라지고 그 범위를 인지하고 사용할 것.

out or pageContext: 해당 페이지 안에서만 데이터 공유 (private과 유사)

requset : URL 주소가 변경되면 데이터가 초기화 된다.

session : 브라우저가 닫히기 전까지 데이터를 공유. 브라우저 차원에서 각각 다른 데이터 예)로그인 데이터
컨트롤러에서 로그인을 시켜주고 페이지를 변경해주면됨
장바구니 추가 시 추가 후 페이지를 변경해주면됨.
브라우저가 바뀌기 전까지 공유해서 쓸 수 있다.

application : 프로젝트 차원에서 같은 데이터 공유 (브라우저가 달라도 사용가능)
브라우저가 달라도 상관없이 사이트 안에서 같은 데이터 공유
커넥션풀, 세마포어 등
 -->
 
 <%
if(session.getAttribute("user") == null){
	out.println("<h3>로그인 하세요!</h3>");
%>
	<form action="login.do" border="1" method = "POST">
		<fieldset>
			<table>
				<tr>
					<td>아이디</td>
					<td><input type = "text" name = "id" /></td>
					<td colsapan ="2"><input type = "submit" value = "login"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type = "password" name = "password" /></td>
				</tr>
			</table>
		</fieldset>
	</form>

<%
}else{
	String user = (String)session.getAttribute("user");
	out.println(user+"님이 로그인 하셨습니다!");
	out.println("<li><a href=\"logout.do\">로그아웃</a></li>");
}
 %>
</body>
</html>