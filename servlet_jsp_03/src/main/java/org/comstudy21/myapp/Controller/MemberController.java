package org.comstudy21.myapp.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberController extends HttpServlet {
	// jsp 페이지에서 공유되는 session 선언 (같은 브라우저 상에서)
	protected HttpSession session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet - MemberController");
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		String path = reqUri.substring(ctxPath.length());

		String viewName = "/WEB-INF/views/member/login.jsp";
		if ("/member/joinus.do".equals(path)) {
			viewName = "/WEB-INF/views/member/joinus.jsp";
		} else if ("/member/login.do".equals(path)) {
			// URL에 적용되는 것은 모두 GET방식 - login페이지로 forward된다.
			// get방식으로 들어오면 forward만 시킴.
			viewName = "/WEB-INF/views/member/login.jsp";
		} else if ((session!=null)&&"/member/logout.do".equals(path)) {
			// 세션의 내용을 제거한다.
			session.invalidate(); // 세션의 전체 속성을 강제로 제거한다.(초기화)
			resp.sendRedirect("login.do");
			// forward 부분이 실행 안되도록 바로 종료
			return;
		}

		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		String path = reqUri.substring(ctxPath.length());
		if ("/member/login.do".equals(path)) {
			// id와 password 파라미터를 받아서 비교 후 로그인 처리
			// spring과 달리 하나하나 받아야함.
			String id = req.getParameter("id");
			String pwd = req.getParameter("password");
			if("hong".equals(id)&& "1234".equals(pwd)) {
				// 세션에 정보를 등록.
				// jsp 파일에서는 세션을 그냥 사용할 수 있지만 여기서는 그냥 사용할 수 없다.
				// 세션이라는 것을 받아와서 사용해야 함.
				// 참조값을 가져오는 것이므로 생성이 아님.
				session = req.getSession();
				session.setAttribute("user", "hong");
			}
			
			
			
			resp.sendRedirect("login.do"); // doGet()으로 갱신된다.
		}
	}
}
