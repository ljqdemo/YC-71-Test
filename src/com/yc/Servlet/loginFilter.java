package com.yc.Servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * ����Ȩ�޿��ƵĹ�����
 * ���ض��jsp urlPatterns={"/a.jsp","/b.jsp"...}
 */

//�˴���дҪ���ص�ҳ���ַ   
@WebFilter("/userinfo.jsp")
public class loginFilter implements Filter {

    


	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ���˷���
	 * FilterChain chain ��������
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httprequest=(HttpServletRequest) request;
		if(httprequest.getSession().getAttribute("loginUser")==null){
			//���δ��¼����ת��½
			request.setAttribute("msg", "���ȵ�¼��");
			request.getRequestDispatcher("login.jsp").forward(request, response );
			//�м�return
			return ;
		}
		//��������±���ִ�еķ���
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
