<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户登录表单        -->
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="loginForm" action="user.s" method="post">
	<font color="red"><%=request.getAttribute("msg") %></font>
	<input type="hidden" name="op" value="login">
		<br/>用户名 &nbsp;<INPUT class="input" tabIndex="1"  type="text"      maxLength="20" size="35" name="name">
		<br/>密　码 &nbsp;<INPUT class="input" tabIndex="2"  type="password"  maxLength="20" size="40" name="password">
		<br/>验证码 &nbsp;<INPUT class="input" tabIndex="2"  type="text"  maxLength="4" size="10" name="vcode">
		<img alt="" src="vcode.jsp">
		<br/><INPUT class="btn"  tabIndex="6"  type="submit" value="登 录">
	</FORM>
</DIV>
<!--      声明        -->
<%@include file="buttom.jsp" %>
