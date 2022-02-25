<%@page import="org.comstudy21.myapp.model.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>제품 상세 보기</h1>
<!-- 장바구니 담기 OR 즉시 구매하기 -->
<%
if(session.getAttribute("user")==null) {
	out.println("<h3>로그인 하세요!</h3>");
%>
	<jsp:include page="/inc/menu.jsp"></jsp:include>
<%
} else {
	ProductDto product = (ProductDto)request.getAttribute("productDto");
%>
	<jsp:include page="/inc/logout.jsp"></jsp:include>
	<table border="1" width="550">
		<tr>
			<th width="30%">제품번호</th>
			<td width="70%"><%=product.getNo() %></td>
		</tr>
		<tr>
			<th>제품명</th>
			<td><%=product.getpName() %></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><%=product.getPrice() %></td>
		</tr>
		<tr>
			<th>수량</th>
			<td>
				<select id="ea" name="ea"> 
					<option value="1" <% if(product.getEa()==1) out.print("selected = \"selected\""); %>>1</option>
					<option value="2" <% if(product.getEa()==2) out.print("selected = \"selected\""); %>>2</option>
					<option value="3" <% if(product.getEa()==3) out.print("selected = \"selected\""); %>>3</option>
					<option value="4" <% if(product.getEa()==4) out.print("selected = \"selected\""); %>>4</option>
					<option value="5" <% if(product.getEa()==5) out.print("selected = \"selected\""); %>>5</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>합계</th>
			<td>0원</td>
		</tr>
	</table>
	<hr>
	<a href="#" onclick="sendData()">수정</a> | <a href="cart_list.do">이전</a>
	<script>
	function sendData() {
		event.preventDefault(); // a의 기본기능이 일시 정지 된다.
		var ea = document.getElementById("ea").value;
		location.href="cart_modifyEa.do?pno=<%=product.getNo()%>&ea=" + ea;
	}
	</script>
<%
}
%>


</body>
</html>