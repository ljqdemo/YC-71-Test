package com.yc.Servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义Servlet基类
 * 
 *
 */
public abstract class  BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String op=request.getParameter("op");
		//在父类中通过op字段调用子类的方法  java的黑科技 -----反射
		//Class 可以动态的调用方法，获取属性
		//获取当前类对象
		try {
			Method method=this.getClass().getMethod(op, HttpServletRequest.class
					,HttpServletResponse.class);
			//执行方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			throw new ServletException("动态调用方法失败"+op,e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
