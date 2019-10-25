<%@page import="java.sql.Timestamp"%>
<%@page import="com.yc.dao.DBHelper"%>
<%@page import="com.jspsmart.upload.*"%>
<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    //创建上传对象
	SmartUpload su = new SmartUpload();
    //初始化
    su.initialize(pageContext);
    //限制上传文件类型
    su.setAllowedFilesList("jpg,png,gif,bmp");
    //限制上传文件的大小
    su.setMaxFileSize(1024*1024*10);  //10M
    su.setCharset("utf-8");
    //获取工程指定的目录的磁盘路径
    String realPath=application.getRealPath("/upload/");
    //执行上传
    su.upload();
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
        file.saveAs(realPath+filename);
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
%>
<% if(msg==null){
	
	response.sendRedirect("login.jsp");
}else{%>
	注册失败！原因：<%=msg %>
<%}%>