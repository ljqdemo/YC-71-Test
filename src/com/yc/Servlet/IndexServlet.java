package com.yc.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.DBHelper;


@WebServlet("/Index.s")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql="select a.boardname pname, b.boardname cname from tbl_board a \n" +
				"join tbl_board b on b.parentid=a.boardid";
		List<Map<String,Object>> boardList=DBHelper.executeQuery(sql);
		request.setAttribute("boardList", boardList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
