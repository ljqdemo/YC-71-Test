package com.yc.Servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.dao.DBHelper;


@WebServlet("/reg.s")
public class regServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����ϴ�����
		SmartUpload su = new SmartUpload();
		
		//JspFactory jsp1 = JspFactory.getDefaultFactory();
		//PageContext pageContext = jsp1.getPageContext(this, request, response,null,true,8192, true);
		//��ʼ��
	    su.initialize(this,request,response);
	    //�����ϴ��ļ�����
	    su.setAllowedFilesList("jpg,png,gif,bmp");
	    //�����ϴ��ļ��Ĵ�С
	    su.setMaxFileSize(1024*1024*10);  //10M
	    su.setCharset("utf-8");
	    //��ȡ����ָ����Ŀ¼�Ĵ���·��
	    //application.getRealPath("/upload/"); ��servlet�е�this.getServletContext()===jsp�е�application 
	    this.getServletContext().getRealPath("/upload/");
	    String realPath= this.getServletContext().getRealPath("/upload/"); 
	    //ִ���ϴ�
	    try {
			su.upload();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String head;
	    //���δ�ύ�ϴ���ͷ������ϵͳͷ��
	    if(su.getFiles().getSize()==0){
	    	head=su.getRequest().getParameter("head");
	    }else{
	    	  //�����ļ�
	        //su.save("/upload/");
	        //��ȡ�ϴ��ļ�����
	        Files files=su.getFiles();
	        //��ȡ�ϴ��ĵ�һ���ļ�
	        File file=files.getFile(0);
	        //��ȡ�ϴ����ļ���
	        String filename=file.getFileName();
	        try {
				file.saveAs(realPath+filename);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        head="upload/"+filename;
	    }
	    //��ȡ���ύ������
	    
	    String uName=su.getRequest().getParameter("uName");
	    String uPass=su.getRequest().getParameter("uPass");
	    String uPass1=su.getRequest().getParameter("uPass1");
	    String gender=su.getRequest().getParameter("gender");
	    String msg=null;
	    if(uPass ==null||uPass.trim().isEmpty()){
	    	msg="������д���룡";
	    }else if(uPass==null|| uPass.equals(uPass1)==false){
	    	msg="���������벻һ�£�";
	    }else{
	    	String sql="insert into tbl_user values (null,?,?,?,?,?)";
	    	DBHelper.executeUpdate(sql,
	    			uName,uPass,head,
	    			new Timestamp(System.currentTimeMillis())
	    			,gender);
	    }
	     if(msg==null){
	    	
	    	response.sendRedirect("login.jsp");
	    }else{
	    	response.setContentType("text/html; charset=GBK");
	    	response.getWriter().print("ע��ʧ��ԭ��"+msg);;
	    	
	   }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
