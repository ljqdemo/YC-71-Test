<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<TITLE>论坛</TITLE>
<Link rel="stylesheet" type="text/css" href="style/style.css" />
</HEAD>

<BODY>

<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->

<DIV class="h">
<% if(session.getAttribute("loginUser")==null){ %>
	您尚未　<a href="login.jsp">登录</a>
	&nbsp;| &nbsp; <A href="reg.jsp">注册</A> |
<% }else{%>
	欢迎: ${loginUser.uname} 
	<img alt="" src="${ loginUser.head} "
	height="30px" width="30px">
	| <a href="userinfo.jsp">我的信息</a>
	| <a href="user.s?op=logout">退出</a>
<%} %>
	| 当前再线人数：${onlineUsers.size() } 
</DIV>

<!--      主体        -->
<DIV class="t">
	<TABLE cellSpacing="0" cellPadding="0" width="100%">
		<TR class="tr2" align="center">
			<TD colSpan="2">论坛</TD>
			<TD style="WIDTH: 10%;">主题</TD>
			<TD style="WIDTH: 30%">最后发表</TD>
		</TR>
	

		
		
		
	<!--       子版块       -->
		<% 
			List<Map<String,Object>> boardList=(List<Map<String,Object>>)request.getAttribute("boardList");
				//直接访问index.jsp会出项空指针
				if(boardList==null){
					request.getRequestDispatcher("Index.s").forward(request, response);
					return ;
				}
		
			String  pname="";
			for(Map<String,Object> board:boardList){
		%>
		<% 
				if(pname.equals(board.get("pname"))==false){
					pname=(String)board.get("pname");
				
		%>
		<!--       主版块       -->
		<TR class="tr3">
			<TD colspan="4"><%=pname%></TD>
		</TR>
		<%} %>>
		<TR class="tr3">
			<TD width="5%">&nbsp;</TD>
			<TH align="left">
				<IMG src="image/board.gif">
				<A href="list.jsp"><%=board.get("cname") %></A>
			</TH>
			<TD align="center">30</TD>
			<TH>
				<SPAN>
					<A href="detail.jsp">c#是微软开发的语言 </A>
				</SPAN>
				<BR/>
				<SPAN>accp</SPAN>
				<SPAN class="gray">[ 2007-07-30 10:25 ]</SPAN>
			</TH>
		</TR>
		<% }%>

</DIV>

<BR/>
<CENTER class="gray">源辰信息</CENTER>
</BODY>
</HTML>
