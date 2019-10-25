package com.yc.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.BizException;
import com.yc.biz.TblBoardBiz;
import com.yc.biz.TblUserBiz;
import com.yc.dao.DBHelper;
import com.yc.dao.TblUserDao;


@WebServlet("/dologin.s")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");;
				response.setCharacterEncoding("utf-8");
		
				//获取Session对象	
				HttpSession session=request.getSession();
		
				//获取会话中的验证码
				String svcode = (String) session.getAttribute("vcode");
				//获取请求参数中的验证码
				String rvcode = request.getParameter("vcode");
				
				if (svcode.equals(rvcode) == false) {
					request.setAttribute("msg", "请输入正确的验证码");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String age = request.getParameter("age");
				
				//TblUserDao dao=new TblUserDao();
				TblUserBiz biz=new TblUserBiz();
				
				try {
					Map<String,Object> user=biz.login(name, password);
					 request.getSession().setAttribute("loginUser", user.get(0)); //创建cookie对象
					 Cookie cookie = new Cookie("loginedUserName", name);
					 	int maxAge = age == null ? 0 : Integer.parseInt(age);
					  //将cookie设置有效时间为三分钟，设置0表示删除该cookie cookie.setMaxAge(maxAge);
					  //将cookie添加到响应对象中 response.addCookie(cookie); //登录成功
					  response.sendRedirect("index.jsp"); 
				} catch (BizException e) {
					e.printStackTrace();
					request.setAttribute("msg",e.getMessage());
				    request.getRequestDispatcher("login.jsp").forward(request,response);
				}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
