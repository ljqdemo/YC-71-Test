

<%@page import="java.util.*"%>
<%@page import="com.yc.dao.DBHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<TITLE>论坛--帖子列表</TITLE>

<Link rel="stylesheet" type="text/css" href="style/style.css" />
</HEAD>
<%
	String sql = "select * from tbl_topic";
	//每页10条记录
	int pageRows = 10;
	//第几页
	int pageNumber = 1;

	//从请求获取查询分页参数
	String pageNumberParm=request.getParameter("pageNumber");
	if(pageNumberParm!=null){
		 pageNumber=Integer.parseInt(pageNumberParm);
	}
	
	
	List<Map<String, Object>> list = DBHelper.queryPageMysql(sql, pageNumber, pageRows);
%>
<BODY>
	<DIV>
		<IMG src="image/logo.gif">
	</DIV>
	<!--      用户信息、登录、注册        -->

	<DIV class="h">
		您尚未 <a href="login.jsp">登录</a> &nbsp;| &nbsp; <A href="reg.jsp">注册</A>
		|
	</DIV>


	<!--      主体        -->
	<DIV>
		<!--      导航        -->
		<br />
		<DIV>
			&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt; <B><a
				href="list.jsp">灌水乐园</a></B>
		</DIV>
		<br />
		<!--      新帖        -->
		<DIV>
			<A href="post.jsp"><IMG src="image/post.gif" name="td_post"
				border="0" id=td_post></A>
		</DIV>
		<!--         翻 页         -->
		<DIV>
			<a href="list.jsp?pageNumber=<%=pageNumber-1%>">上一页</a>| <a href="list.jsp?pageNumber=<%=pageNumber+1%>">下一页</a>
		</DIV>

		<DIV class="t">
			<TABLE cellSpacing="0" cellPadding="0" width="100%">
				<TR>
					<TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
				</TR>
				<!--       表 头           -->
				<TR class="tr2">
					<TD>&nbsp;</TD>
					<TD style="WIDTH: 80%" align="center">文章</TD>
					<TD style="WIDTH: 10%" align="center">作者</TD>
					<TD style="WIDTH: 10%" align="center">回复</TD>
				</TR>
				<!--         主 题 列 表        -->
				<%
					for (Map<String, Object> row : list) {
				%>
				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp"><%=row.get("title")%></A>
					</TD>
					<TD align="center"><%=row.get("uid")%></TD>
					<TD align="center">????</TD>
				</TR>
				<%
					}
				%>
				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">哈哈</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">1</TD>
				</TR>

				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">发水了</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">0</TD>
				</TR>

				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">发水</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">0</TD>
				</TR>

				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">笑话5则</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">0</TD>
				</TR>

				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">最新大片</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">0</TD>
				</TR>

				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">纯净水</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">0</TD>
				</TR>

				<TR class="tr3">
					<TD><IMG src="image/topic.gif" border=0></TD>
					<TD style="FONT-SIZE: 15px"><A href="detail.jsp">这边风景好</A></TD>
					<TD align="center">abc</TD>
					<TD align="center">0</TD>
				</TR>

			</TABLE>
		</DIV>
		<!--            翻 页          -->
		<DIV>
			<a href="list.jsp">上一页</a>| <a href="list.jsp">下一页</a>
		</DIV>
	</DIV>
	<!--             声 明          -->
	<BR />
	<CENTER class="gray">源辰信息</CENTER>
</BODY>
</HTML>
