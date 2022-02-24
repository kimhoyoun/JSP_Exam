package org.comstudy21.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 각 컨트롤러를 만들기위해 추상 컨트롤러를 만듦.
// 그리고 각 컨트롤러를 만들고 맵에 넣어서 사용.
public abstract class Controller {
	public abstract String action(HttpServletRequest req, HttpServletResponse resp);
}
