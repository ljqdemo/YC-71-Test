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
		
				//��ȡSession����	
				HttpSession session=request.getSession();
		
				//��ȡ�Ự�е���֤��
				String svcode = (String) session.getAttribute("vcode");
				//��ȡ��������е���֤��
				String rvcode = request.getParameter("vcode");
				
				if (svcode.equals(rvcode) == false) {
					request.setAttribute("msg", "��������ȷ����֤��");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String age = request.getParameter("age");
				
				//TblUserDao dao=new TblUserDao();
				TblUserBiz biz=new TblUserBiz();
				
				try {
					Map<String,Object> user=biz.login(name, password);
					 request.getSession().setAttribute("loginUser", user.get(0)); //����cookie����
					 Cookie cookie = new Cookie("loginedUserName", name);
					 	int maxAge = age == null ? 0 : Integer.parseInt(age);
					  //��cookie������Чʱ��Ϊ�����ӣ�����0��ʾɾ����cookie cookie.setMaxAge(maxAge);
					  //��cookie��ӵ���Ӧ������ response.addCookie(cookie); //��¼�ɹ�
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
