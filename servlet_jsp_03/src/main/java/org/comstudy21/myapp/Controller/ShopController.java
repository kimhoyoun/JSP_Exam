package org.comstudy21.myapp.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.comstudy21.myapp.model.ProductDto;

public class ShopController extends HttpServlet {
	private static ArrayList<ProductDto> pList = new ArrayList<>();
	protected HttpSession session;
	protected ArrayList<ProductDto> cartList = new ArrayList<>();

	static {
		pList.add(new ProductDto(ProductDto.nextSeq(), "세탁기", 200, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "냉장고", 400, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "오디오", 100, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "청소기", 50, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "비디오", 80, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "테레비", 300, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "면도기", 20, 1));
		pList.add(new ProductDto(ProductDto.nextSeq(), "인덕션", 200, 1));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet - ShopController");
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath();
		String path = reqUri.substring(ctxPath.length());
		String viewName = "/WEB-INF/views/shop/product_list.jsp";
		if ("/shop/cart.do".equals(path)) {
			if(req.getParameter("no") != null) {
				if ("/shop/cart.do".equals(path)) {
					// 세션이 null이면 새로 가져옴
					if (session == null) {
						session = req.getSession();
					}

					int no = Integer.parseInt(req.getParameter("no"));
					int i = pList.indexOf(new ProductDto(no, "", 0, 0));
					ProductDto dto = pList.get(i);
					// list에 같은 객체가 존재한다면
					if (cartList.contains(dto)) {
						// 인덱스 추출후
						int index = cartList.indexOf(dto);
						// 객체의 ea 1증가
						cartList.get(index).setEa(cartList.get(index).getEa() + 1);
					}else {
						// 없으면 그대로 추가
						cartList.add(dto);
					}
					
					// session에 cartList 컬렉션이 있는지 확인.
					// 세션에 List가 없다면
					if (session.getAttribute("cartList") == null) {
						// 세션에 cartList 추가
						session.setAttribute("cartList", cartList);
					}
				}
			}
			viewName = "/WEB-INF/views/shop/cart_list.jsp";
		} else if ("/shop/product_list.do".equals(path)) {
			req.setAttribute("pList", pList);
			viewName = "/WEB-INF/views/shop/product_list.jsp";
		} else if ("/shop/product_detail.do".equals(path)) {
			int no = Integer.parseInt(req.getParameter("no"));
			int i = pList.indexOf(new ProductDto(no, "", 0, 0));
			req.setAttribute("productDto", pList.get(i));
			viewName = "/WEB-INF/views/shop/product_detail.jsp";
		}

		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String reqUri = req.getRequestURI();
//		String ctxPath = req.getContextPath();
//		String path = reqUri.substring(ctxPath.length());
//
//		
//	}
}
