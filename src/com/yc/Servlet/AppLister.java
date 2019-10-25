package com.yc.Servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 应用上下文的生命周期的监听器
 *
 */
@WebListener
public class AppLister implements ServletContextListener {

    
	/**
     * @see 销毁
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         System.out.println("========destory======");
         
    }

	/**
     * @see 初始化的方法
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	  System.out.println("========init======");
    }
	
}
