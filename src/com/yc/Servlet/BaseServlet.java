package com.yc.Servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * �Զ���Servlet����
 * 
 *
 */
public abstract class  BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String op=request.getParameter("op");
		//�ڸ�����ͨ��op�ֶε�������ķ���  java�ĺڿƼ� -----����
		//Class ���Զ�̬�ĵ��÷�������ȡ����
		//��ȡ��ǰ�����
		try {
			Method method=this.getClass().getMethod(op, HttpServletRequest.class
					,HttpServletResponse.class);
			//ִ�з���
			method.invoke(this, request,response);
		} catch (Exception e) {
			throw new ServletException("��̬���÷���ʧ��"+op,e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
