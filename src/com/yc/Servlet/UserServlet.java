package com.yc.Servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.bean.BizException;
import com.yc.biz.TblUserBiz;
import com.yc.dao.DBHelper;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
  //
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		
		  //ָ�����뼯 
			request.setCharacterEncoding("utf-8");
		  response.setCharacterEncoding("utf-8");
		
		//��ȡSession����	
		HttpSession session=request.getSession();
		//��ȡ�Ự�е���֤��
		String svcode = (String) session.getAttribute("vcode");
		//��ȡ��������е���֤��
		String rvcode = request.getParameter("vcode");
		
		if ( svcode ==null||svcode.equals(rvcode) == false) {
			request.setAttribute("msg", "��������ȷ����֤��");
			try {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
		
				e.printStackTrace();
			}
		}

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		TblUserBiz biz=new TblUserBiz();
		
		try {
			Map<String,Object> user=biz.login(name, password);
			 request.getSession().setAttribute("loginUser", user); //����cookie����
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
	//ע��
	public void reg(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
				//ָ�����뼯
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
		
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
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//�õ�ǰ�Ự��������
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
}
