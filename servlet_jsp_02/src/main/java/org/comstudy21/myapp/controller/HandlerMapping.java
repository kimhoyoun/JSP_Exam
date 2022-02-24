package org.comstudy21.myapp.controller;

import java.util.HashMap;

// 컨트롤러를 저장해 놓는 공간
// 생성할 때 컨트롤러가 만들어져 있어야 함.
// HashMap을 만들고 저장
public class HandlerMapping {
	HashMap<String, Controller> mappings = new HashMap<>();
	
	public HandlerMapping() {
		mappings.put("/home", new HomeController());
		mappings.put("/board/list", new BoardController());
		mappings.put("/member/list", new MemberController());
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
