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
 * 访问权限控制的过滤器
 * 拦截多个jsp urlPatterns={"/a.jsp","/b.jsp"...}
 */

//此处填写要拦截的页面地址   
@WebFilter("/userinfo.jsp")
public class loginFilter implements Filter {

    


	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see 过滤方法
	 * FilterChain chain 过滤器链
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httprequest=(HttpServletRequest) request;
		if(httprequest.getSession().getAttribute("loginUser")==null){
			//如果未登录则跳转登陆
			request.setAttribute("msg", "请先登录！");
			request.getRequestDispatcher("login.jsp").forward(request, response );
			//切记return
			return ;
		}
		//正常情况下必须执行的方法
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
