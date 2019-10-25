package com.yc.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * 
 * @author DELL
 *创建Servlet
 *1.继承 HttpServlet
 *2.实现（重写）  doGet  doPost
 *3.配置   web.xml  注解方式（常用）
 */
@WebServlet("/hello1.s")
public class HelloServlet extends HttpServlet{

		private int count=0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		count++;
		resp.getWriter().print("Hello world"+"第"+count+"次访问！");
		System.out.println("====doGet===="+"第"+count+"次访问！");
	}

	@Override
	public void destroy() {
		System.out.println("销毁！");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("初始化");
	}

	
	

}
