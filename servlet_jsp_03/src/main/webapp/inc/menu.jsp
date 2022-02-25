<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<ul>
	<li><a href="<%=request.getContextPath()%>/member/joinus.do">회원가입</a></li>
	<li><a href="<%=request.getContextPath()%>/member/login.do">로그인</a></li>
	<li><a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a></li>
	<li><a href="<%=request.getContextPath()%>/shop/product_list.do">상품목록</a></li>
	<li><a href="<%=request.getContextPath()%>/shop/cart.do">장바구니</a></li>
</ul>
</body>
</html>