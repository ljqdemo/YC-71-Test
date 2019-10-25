<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户注册表单        -->
<DIV  class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="regForm" action="user.s?op=reg" method="post" enctype="multipart/form-data">
	
		<br/>用&nbsp;户&nbsp;名 &nbsp;
			<INPUT class="input" tabIndex="1" type="text" maxLength="20" size="35" name="uName">
		<br/>密&nbsp;&nbsp;&nbsp;&nbsp;码 &nbsp;
			<INPUT class="input" tabIndex="2" type="password" maxLength="20" size="40" name="uPass">
		<br/>重复密码 &nbsp;
			<INPUT class="input" tabIndex="3" type="password" maxLength="20" size="40" name="uPass1">
		<br/>性别 &nbsp;
			女<input type="radio" name="gender" value="1">
			男<input type="radio" name="gender" value="2" checked="checked" />
		<br/>上传头像 &nbsp;
			<INPUT class="input" tabIndex="3" type="file" maxLength="20" size="40" name="head">	
			
		<br/>请选择头像 <br/>
		<image src="image/head/1.gif"/><input type="radio" name="head" value="image/head/1.gif" checked="checked">
		<img src="image/head/2.gif"/><input type="radio" name="head" value="image/head/2.gif">
		<img src="image/head/3.gif"/><input type="radio" name="head" value="image/head/3.gif">
		<img src="image/head/4.gif"/><input type="radio" name="head" value="image/head/4.gif">
		<img src="image/head/5.gif"/><input type="radio" name="head" value="image/head/5.gif">
		<BR/>
		<img src="image/head/6.gif"/><input type="radio" name="head" value="image/head/6.gif">
		<img src="image/head/7.gif"/><input type="radio" name="head" value="image/head/7.gif">
		<img src="image/head/8.gif"/><input type="radio" name="head" value="image/head/8.gif">
		<img src="image/head/9.gif"/><input type="radio" name="head" value="image/head/9.gif">
		<img src="image/head/10.gif"/><input type="radio" name="head" value="image/head/10.gif">
		<BR/>
		<img src="image/head/11.gif"/><input type="radio" name="head" value="image/head/11.gif">
		<img src="image/head/12.gif"/><input type="radio" name="head" value="image/head/12.gif">
		<img src="image/head/13.gif"/><input type="radio" name="head" value="image/head/13.gif">
		<img src="image/head/14.gif"/><input type="radio" name="head" value="image/head/14.gif">
		<img src="image/head/15.gif"/><input type="radio" name="head" value="image/head/15.gif">
		<br/>
			<INPUT class="btn" tabIndex="4" type="submit" value="注 册">
	</FORM>
</DIV>
<!--      声明        -->
<%@include file="buttom.jsp" %>

