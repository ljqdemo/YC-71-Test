package com.yc.Servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Ӧ�������ĵ��������ڵļ�����
 *
 */
@WebListener
public class AppLister implements ServletContextListener {

    
	/**
     * @see ����
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         System.out.println("========destory======");
         
    }

	/**
     * @see ��ʼ���ķ���
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	  System.out.println("========init======");
    }
	
}
