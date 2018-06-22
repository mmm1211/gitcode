package com.oes.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.oes.bean.Questions;
import com.oes.biz.IQuestionsBiz;
import com.oes.biz.impl.QuestionsBizImpl;
import com.oes.util.FileUploadUtil;
import com.oes.util.ReadExcelToDB;
import com.oes.util.StringUtil;

/**
 * Servlet implementation class QuestionsServlet
 */
@WebServlet("/QuestionsServlet")
public class QuestionsServlet extends BasicServlet {
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
		} else if ("addQuestinos".equals(op)) {
			addQuestinos(request, response);
		} else if ("addQuestion".equals(op)) {
			addQuestion(request, response);
		} else if ("updateQuestions".equals(op)) {
			updateQuestions(request, response);
		} else if ("updateQuestion".equals(op)) {
			updateQuestion(request, response);
		} else if ("findByCondition".equals(op)) {
			findByCondition(request, response);
		} else if ("findByQid".equals(op)) {
			findByQid(request, response);
		} else if ("findByQids".equals(op)) {
			findByQids(request, response);
		} else if ("getInfos".equals(op)) {
			getInfos(request, response);
		} else if ("findQuestionNumByCid".equals(op)) {
			findQuestionNumByCid(request, response);
		}
	}

	/**
	 * 查询该课程题目数量
	 * 
	 * @param request
	 * @param response
	 */
	private void findQuestionNumByCid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid"); // 获取课程编号
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		List<Map<String, String>> list = questionsBiz.getTotals(cid);
		this.send(response, list);
	}

	private void getInfos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		this.send(response, questionsBiz.getInfos());
	}

	/**
	 * 修改题目状态
	 * 
	 * @param request
	 * @param response
	 */
	private void updateQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qids = request.getParameter("qids");
		String status = request.getParameter("status");
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		this.send(response, questionsBiz.updateQuestion(qids, status));

	}

	/**
	 * 通过多个题目编号查找这些题目
	 * 
	 * @param request
	 * @param response
	 */
	private void findByQids(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qids = request.getParameter("qids");

		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		this.send(response, questionsBiz.findByQid(qids));
	}

	/**
	 * 通过题目编号查找该题目
	 * 
	 * @param request
	 * @param response
	 */
	private void findByQid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qid = request.getParameter("qid");

		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		this.send(response, questionsBiz.findByQid(qid));
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
		String cid = request.getParameter("cid");
		String qname = request.getParameter("qname");

		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		Map<String, Object> map = questionsBiz.findByCondition(tid, cid, qname, pageNo, pageSize);
		this.send(response, map);
	}

	/**
	 * 修改题目
	 * 
	 * @param request
	 * @param response
	 */
	private void updateQuestions(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String qid = request.getParameter("qid");
		String tid = request.getParameter("tid");
		String cid = request.getParameter("cid");
		String qname = request.getParameter("qname");
		String ans1 = request.getParameter("ans1");
		String ans2 = request.getParameter("ans2");
		String ans3 = request.getParameter("ans3");
		String ans4 = request.getParameter("ans4");
		String ans = request.getParameter("ans");
		Questions questions = new Questions();
		questions.setQid(qid);
		questions.setTid(Integer.parseInt(tid));
		questions.setCid(Integer.parseInt(cid));
		questions.setQname(qname);
		questions.setAns(ans);
		questions.setAns1(ans1);
		questions.setAns2(ans2);
		questions.setAns3(ans3);
		questions.setAns4(ans4);
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		this.send(response, questionsBiz.updateQuestions(questions));
	}

	/**
	 * 单个添加题目
	 * 
	 * @param request
	 * @param response
	 */
	private void addQuestion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String tid = request.getParameter("tid");

		String cid = request.getParameter("cid");
		String qname = request.getParameter("qname");
		String ans1 = request.getParameter("ans1");
		String ans2 = request.getParameter("ans2");
		String ans3 = request.getParameter("ans3");
		String ans4 = request.getParameter("ans4");
		String ans = request.getParameter("ans");
		Questions questions = new Questions();
		questions.setTid(Integer.parseInt(tid));
		questions.setCid(Integer.parseInt(cid));
		questions.setQname(qname);
		questions.setAns(ans);
		questions.setAns1(ans1);
		questions.setAns2(ans2);
		questions.setAns3(ans3);
		questions.setAns4(ans4);
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		this.send(response, questionsBiz.addQuestions(questions));

	}

	/**
	 * 批量添加题目
	 * 
	 * @param request
	 * @param response
	 */
	private void addQuestinos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FileUploadUtil uploadUtil = new FileUploadUtil();
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		String filePath = uploadUtil.uploadExcelFile(pageContext);
		if (StringUtil.isNull(filePath)) {
			this.send(response, -1);
		} else {
			File fl = new File(pageContext.getServletContext().getRealPath("/") + filePath);
			if (fl.exists()) {
				ReadExcelToDB retdb = new ReadExcelToDB();
				List<List<String>> list = retdb.importExcel(fl);
				fl.deleteOnExit();
				this.send(response, questionsBiz.addQuestions(list));
			}
		}

	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		IQuestionsBiz questionsBiz = new QuestionsBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> map = questionsBiz.findByPage(pageNo, pageSize);
		this.send(response, map);
	}

}
