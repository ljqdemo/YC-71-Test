package com.yc.Servlet;

import java.util.ArrayList;
import java.util.*;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.User;

/**
 * 在线人数统计
 *
 */
@WebListener
public class OnlineLister implements HttpSessionAttributeListener,HttpSessionListener {


	/**
     * @see 增加
     */
    public void attributeAdded(HttpSessionBindingEvent e)  { 
       /**
        * 可以存数据的对象：
         * 页面上下文
        * 请求
        * 会话对象
        * 应用上下文
        */
    	if ("loginUser".equals(e.getName())) {
			//将用户存入引用上下文对象中
    		ServletContext application=e.getSession().getServletContext();
    		List<Map<String,Object>> list=(List<Map<String,Object>>)application.getAttribute("onlineUsers");
    		if(list==null) {
    			list=new ArrayList<>();
    		}
    		//增加判断：如果list集合中包含了用户的Map则不计入在线人数
    		boolean contains=false;
    		//获取登陆的用户名
    		String uname=(String)((Map<String,Object>) e.getValue()).get("uname");
    		for(Map<String,Object> user:list) {
    			//遍历每个登陆的用户和当前的用户名进行比较
    			if(user.get("uname").equals(uname)) {
    				contains=true;
    				break;
    			}
    	}
    		if(contains==false) {
    		list.add((Map<String,Object>) e.getValue());
    		application.setAttribute("onlineUsers", list);
    		}
    	} 
    }

	/**
     * @see 移除
     */
    public void attributeRemoved(HttpSessionBindingEvent e)  { 
         
    }

	/**
     * @see 替换
     */
    public void attributeReplaced(HttpSessionBindingEvent e)  { 
      
    }

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}
	//会话销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {

			//将用户存入引用上下文对象中
    		ServletContext application=e.getSession().getServletContext();
    		List<Map<String,Object>> list=(List<Map<String,Object>>)application.getAttribute("onlineUsers");
    		if(list==null) {
    			list=new ArrayList<>();
    		}
    		//获取登陆的用户名
    		Map<String,Object> user1=(Map<String,Object>) e.getSession().getAttribute("loginUser");
    		String uname=(String) user1.get("uname");
    		for(int i=0;i<list.size();i++) {
    			Map<String,Object> user2=list.get(i);
    			//遍历每个登陆的用户和当前的用户进行比较
    			if(user2.get("uname").equals(uname)) {
    				list.remove(i);
    				break;
    			}
    		}
  
    	} 
		
	
}
