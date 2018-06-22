package com.oes.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oes.bean.QuestionTypes;
import com.oes.biz.IQuestionTypesBiz;
import com.oes.biz.impl.QuestionTypesBizImpl;

/**
 * Servlet implementation class QuestionTypesServlet
 */
@WebServlet("/QuestionTypesServlet")
public class QuestionTypesServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");
		if ("findByPage".equals(op)) {
			findByPage(request, response);
		} else if ("findAll".equals(op)) {
			findAll(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		}
	}

	/**
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		String tid = request.getParameter("tid");
		String tname = request.getParameter("tname");
		IQuestionTypesBiz questionTypesBiz = new QuestionTypesBizImpl();
		Map<String, Object> map = questionTypesBiz.findByPage(pageNo, pageSize, tid, tname);
		this.send(response, map);
	}

	/**
	 * 查询所有题目类型
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IQuestionTypesBiz questionTypesBiz = new QuestionTypesBizImpl();
		List<QuestionTypes> list = questionTypesBiz.findAll();
		this.send(response, list);
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IQuestionTypesBiz questionTypesBiz = new QuestionTypesBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = questionTypesBiz.findByPages(pageNo, pageSize);
		this.send(response, map);
	}

}
