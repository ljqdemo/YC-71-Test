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
 * ��������ͳ��
 *
 */
@WebListener
public class OnlineLister implements HttpSessionAttributeListener,HttpSessionListener {


	/**
     * @see ����
     */
    public void attributeAdded(HttpSessionBindingEvent e)  { 
       /**
        * ���Դ����ݵĶ���
         * ҳ��������
        * ����
        * �Ự����
        * Ӧ��������
        */
    	if ("loginUser".equals(e.getName())) {
			//���û��������������Ķ�����
    		ServletContext application=e.getSession().getServletContext();
    		List<Map<String,Object>> list=(List<Map<String,Object>>)application.getAttribute("onlineUsers");
    		if(list==null) {
    			list=new ArrayList<>();
    		}
    		//�����жϣ����list�����а������û���Map�򲻼�����������
    		boolean contains=false;
    		//��ȡ��½���û���
    		String uname=(String)((Map<String,Object>) e.getValue()).get("uname");
    		for(Map<String,Object> user:list) {
    			//����ÿ����½���û��͵�ǰ���û������бȽ�
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
     * @see �Ƴ�
     */
    public void attributeRemoved(HttpSessionBindingEvent e)  { 
         
    }

	/**
     * @see �滻
     */
    public void attributeReplaced(HttpSessionBindingEvent e)  { 
      
    }

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}
	//�Ự����
	@Override
	public void sessionDestroyed(HttpSessionEvent e) {

			//���û��������������Ķ�����
    		ServletContext application=e.getSession().getServletContext();
    		List<Map<String,Object>> list=(List<Map<String,Object>>)application.getAttribute("onlineUsers");
    		if(list==null) {
    			list=new ArrayList<>();
    		}
    		//��ȡ��½���û���
    		Map<String,Object> user1=(Map<String,Object>) e.getSession().getAttribute("loginUser");
    		String uname=(String) user1.get("uname");
    		for(int i=0;i<list.size();i++) {
    			Map<String,Object> user2=list.get(i);
    			//����ÿ����½���û��͵�ǰ���û����бȽ�
    			if(user2.get("uname").equals(uname)) {
    				list.remove(i);
    				break;
    			}
    		}
  
    	} 
		
	
}
