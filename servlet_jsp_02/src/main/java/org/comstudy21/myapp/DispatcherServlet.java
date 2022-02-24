package org.comstudy21.myapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.comstudy21.myapp.controller.Controller;
import org.comstudy21.myapp.controller.HandlerMapping;

public class DispatcherServlet extends HttpServlet {
	public String prefix = "/WEB-INF/views/";
	public String suffix = ".jsp";
	public String viewName = "";
	public HandlerMapping handlerMapping = new HandlerMapping();
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String ctxPath = req.getContextPath();
		String reqUri = req.getRequestURI();
		int beginIndex = ctxPath.length();
		int endIndex = reqUri.lastIndexOf(".");
		String path = reqUri.substring(beginIndex, endIndex);
		System.out.println("path -> "+ path);
		endIndex = path.lastIndexOf("/");
		String dirPath = endIndex== 0 ?"/home": path.substring(0, path.lastIndexOf("/"));
		System.out.println("dirPath -> "+ dirPath);
		
		
		
		// HashMap으로 선언 후 (Handler)
		// Path가 key, Controller가 Value (Handler)
		// Dispatcher에서 핸들러의 getController메서드 실행해서 Controller를 얻음
		// 핸들러를 통해 받은 Controller의 메서드를 실행해서 viewName을 받아옴
		// viewName을 받아오면 forward 시킴.
		// 넘겨줄땐 view를 넘겨줌 (Controller)
		// 각 컨트롤러를 만들고 뷰네임을 반환해주는 메서드를 만든다.(Controller)
		Controller controller =handlerMapping.getController(path);
		viewName = controller.action(req, resp);
		
		
		req.setAttribute("path", path);
		
		RequestDispatcher view = req.getRequestDispatcher(prefix+viewName+suffix);
		view.forward(req, resp);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	public void destroy() {
		System.out.println("destroy - DispatcherServlet");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init - DispatcherServlet");
	}

}
