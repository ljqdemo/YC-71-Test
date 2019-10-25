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
 *����Servlet
 *1.�̳� HttpServlet
 *2.ʵ�֣���д��  doGet  doPost
 *3.����   web.xml  ע�ⷽʽ�����ã�
 */
@WebServlet("/hello1.s")
public class HelloServlet extends HttpServlet{

		private int count=0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		count++;
		resp.getWriter().print("Hello world"+"��"+count+"�η��ʣ�");
		System.out.println("====doGet===="+"��"+count+"�η��ʣ�");
	}

	@Override
	public void destroy() {
		System.out.println("���٣�");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("��ʼ��");
	}

	
	

}
