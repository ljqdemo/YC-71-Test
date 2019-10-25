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
		//��ȡ��ѯ����
		String boardName=request.getParameter("boardName");
		String parentBoard=request.getParameter("parentBoard");
		List<Map<String,Object>> list=biz.query(boardName,parentBoard);
		//��list ת��json �ַ���
		Gson gson=new Gson();
		String json=gson.toJson(list);
		//System.out.println(json);
		//ʹ����Ӧ���� ��� json ���� 
		response.getWriter().append(json);
		
	}
	//���ڱ���
	public void save(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
				//��ȡ��ѯ����
				String boardname=request.getParameter("boardname");
				String parentid=request.getParameter("parentid");
				String boardid=request.getParameter("boardid");
				TblBoardBiz biz=new TblBoardBiz();
				String json=null;
				try {
					if(boardid==null||boardid.trim().isEmpty()) {
						//ʹ��code��Ƿ��ؽ����״̬
					biz.creat(boardname,parentid);
					json= "{ code:1,msg:'�����ӳɹ���'}";
					}else {
						biz.modify(boardname,parentid,boardid);
						json= "{ code:-1,msg:'����޸�	�ɹ���'}";
					}
				} catch (BizException e) {
					
					json="{code:-1,msg:'"+e.getMessage()+"'}";
				}finally {
					response.getWriter().append(json);
				}
		
	}
	//��ҳ��ѯ
	public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("UTF-8");
		//���շ�ҳ���� ��pagination:true,ʱÿ�����󶼻���������� page rows
		int page=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		//��ҳ��ѯ����
		TblBoardBiz biz=new TblBoardBiz(); 
		List<Map<String,Object>> list=biz.pageQuery(page,rows);
	}
}
