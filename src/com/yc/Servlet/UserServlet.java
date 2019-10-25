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
		
		
		  //指定编码集 
			request.setCharacterEncoding("utf-8");
		  response.setCharacterEncoding("utf-8");
		
		//获取Session对象	
		HttpSession session=request.getSession();
		//获取会话中的验证码
		String svcode = (String) session.getAttribute("vcode");
		//获取请求参数中的验证码
		String rvcode = request.getParameter("vcode");
		
		if ( svcode ==null||svcode.equals(rvcode) == false) {
			request.setAttribute("msg", "请输入正确的验证码");
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
			 request.getSession().setAttribute("loginUser", user); //创建cookie对象
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
	//注册
	public void reg(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
				//指定编码集
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
		
				//创建上传对象
				SmartUpload su = new SmartUpload();
				
				//JspFactory jsp1 = JspFactory.getDefaultFactory();
				//PageContext pageContext = jsp1.getPageContext(this, request, response,null,true,8192, true);
				//初始化
			    su.initialize(this,request,response);
			    //限制上传文件类型
			    su.setAllowedFilesList("jpg,png,gif,bmp");
			    //限制上传文件的大小
			    su.setMaxFileSize(1024*1024*10);  //10M
			    su.setCharset("utf-8");
			    //获取工程指定的目录的磁盘路径
			    //application.getRealPath("/upload/"); 在servlet中的this.getServletContext()===jsp中的application 
			    this.getServletContext().getRealPath("/upload/");
			    String realPath= this.getServletContext().getRealPath("/upload/"); 
			    //执行上传
			    try {
					su.upload();
				} catch (SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    String head;
			    //如果未提交上传的头像则用系统头像
			    if(su.getFiles().getSize()==0){
			    	head=su.getRequest().getParameter("head");
			    }else{
			    	  //保存文件
			        //su.save("/upload/");
			        //获取上传文件集合
			        Files files=su.getFiles();
			        //获取上传的第一个文件
			        File file=files.getFile(0);
			        //获取上传的文件名
			        String filename=file.getFileName();
			        try {
						file.saveAs(realPath+filename);
					} catch (SmartUploadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        head="upload/"+filename;
			    }
			    //获取表单提交的数据
			    
			    String uName=su.getRequest().getParameter("uName");
			    String uPass=su.getRequest().getParameter("uPass");
			    String uPass1=su.getRequest().getParameter("uPass1");
			    String gender=su.getRequest().getParameter("gender");
			    String msg=null;
			    if(uPass ==null||uPass.trim().isEmpty()){
			    	msg="亲请填写密码！";
			    }else if(uPass==null|| uPass.equals(uPass1)==false){
			    	msg="亲两次密码不一致！";
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
			    	response.getWriter().print("注册失败原因："+msg);;
			    	
			   }
		
	}
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//让当前会话对象销毁
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
}
