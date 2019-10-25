package com.yc.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.bean.BizException;
import com.yc.biz.TblBoardBiz;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board.s")
public class BoardServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	
	public void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		/*
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8");
		 */
		
		TblBoardBiz biz=new TblBoardBiz(); 
		//获取查询参数
		String boardName=request.getParameter("boardName");
		String parentBoard=request.getParameter("parentBoard");
		List<Map<String,Object>> list=biz.query(boardName,parentBoard);
		//将list 转成json 字符串
		Gson gson=new Gson();
		String json=gson.toJson(list);
		//System.out.println(json);
		//使用相应对象 输出 json 内容 
		response.getWriter().append(json);
		
	}
	//窗口保存
	public void save(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
				//获取查询参数
				String boardname=request.getParameter("boardname");
				String parentid=request.getParameter("parentid");
				String boardid=request.getParameter("boardid");
				TblBoardBiz biz=new TblBoardBiz();
				String json=null;
				try {
					if(boardid==null||boardid.trim().isEmpty()) {
						//使用code标记返回结果的状态
					biz.creat(boardname,parentid);
					json= "{ code:1,msg:'板块添加成功！'}";
					}else {
						biz.modify(boardname,parentid,boardid);
						json= "{ code:-1,msg:'板块修改	成功！'}";
					}
				} catch (BizException e) {
					
					json="{code:-1,msg:'"+e.getMessage()+"'}";
				}finally {
					response.getWriter().append(json);
				}
		
	}
	//分页查询
	public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("UTF-8");
		//接收分页参数 当pagination:true,时每次请求都会带两个参数 page rows
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		//分页查询数据
		TblBoardBiz biz=new TblBoardBiz(); 
		List<Map<String,Object>> list=biz.pageQuery(page,rows);
	}
}
