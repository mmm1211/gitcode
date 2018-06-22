package com.oes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oes.bean.AnsPaper;
import com.oes.bean.ExamPaper;
import com.oes.bean.Questions;
import com.oes.bean.StuInfo;
import com.oes.biz.IAnsPaperBiz;
import com.oes.biz.IExamPaperBiz;
import com.oes.biz.IQuestionsBiz;
import com.oes.biz.impl.AnsPaperBizImpl;
import com.oes.biz.impl.ExamPaperBizImpl;
import com.oes.biz.impl.QuestionsBizImpl;
import com.oes.util.SessionAttributeKey;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/ExamServlet")
public class ExamServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	public ExamServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("joinExam".equals(op)) {
			joinExam(request, response);
		} else if ("detailsExamPaper".equals(op)) {
			detailsExamPaper(request, response);
		} else if ("seeAnsPaper".equals(op)) {
			seeAnsPaper(request, response);

		} else if ("seeBackAnsPaper".equals(op)) {
			seeBackAnsPaper(request, response);

		}
	}

	/**
	 * 教师查看正确答案
	 * 
	 * @param request
	 * @param response
	 */
	private void seeBackAnsPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		String eid = request.getParameter("eid"); // 获取试卷编号
		// 根据试卷编号，获取试卷信息
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		ExamPaper examPaper = examPaperBiz.findExamPaperByEid(eid);
		if (examPaper == null) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('暂无考试信息...');window.close();</script>");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute(SessionAttributeKey.EXAMPAPERINFO, examPaper);
			String subjects = examPaper.getSubjects(); // 获取所有试题信息
			subjects = subjects.replaceAll("-[A-Z0-9a-z\\u4e00-\\u9fa5]{1,}-[\\d]", "");
			// 得到这套试卷的每道题的编号 S_3,S_11,S_5，S_7,S_9
			// 添加单引号
			subjects = "'" + subjects.replace(",", "','") + "'";
			// 得到 ‘S_3’,‘S_11’,‘S_5’，‘S_7’,‘S_9’
			IQuestionsBiz questionBiz = new QuestionsBizImpl();
			List<Questions> list = questionBiz.findByQids(subjects);
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, list);

			try {
				response.sendRedirect("back/manager/page/anspaper.jsp");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 学生查看正确答案
	 * 
	 * @param request
	 * @param response
	 */
	private void seeAnsPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		IQuestionsBiz questionBiz = new QuestionsBizImpl();
		String eid = request.getParameter("eid"); // 获取试卷编号
		// 根据试卷编号，获取试卷信息
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		ExamPaper examPaper = examPaperBiz.findExamPaperByEid(eid);
		if (examPaper == null) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('抱歉，暂时未查询到此套试卷信息...');window.close();</script>");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {

			String subjects = examPaper.getSubjects(); // 获取所有试题信息
			subjects = subjects.replaceAll("-[A-Z0-9a-z\\u4e00-\\u9fa5]{1,}-[\\d]", "");
			// 得到这套试卷的每道题的编号 S_3,S_11,S_5，S_7,S_9
			// 添加单引号
			subjects = "'" + subjects.replace(",", "','") + "'";
			// 得到 ‘S_3’,‘S_11’,‘S_5’，‘S_7’,‘S_9’

			List<Questions> list = questionBiz.findByQids(subjects); // 存放所有问题的list
			// 查询学生每道题作答答案 需要获取 sid eid
			StuInfo stu = (StuInfo) session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
			String sid = stu.getSid();
			IAnsPaperBiz ansPaperBiz = new AnsPaperBizImpl();
			AnsPaper ansPaper = ansPaperBiz.findBySidAndEid(sid, eid);
			String ans = ansPaper.getAns(); // 获取学生答案
			String[] stuAns = ans.split(","); // 获取学生的所有答案
			List<String[]> stuAnsList = new ArrayList<String[]>(); // 创建一个集合，集合里面存放的是每道题的题目编号，学生的答案
			for (int i = 0; i < stuAns.length; i++) {
				String str[] = stuAns[i].split("-"); // 每一道题的答案
				stuAnsList.add(str);
			}
			for (int i = 0; i < list.size(); i++) {
				Questions questions = list.get(i); // 每一道题
				String qid = questions.getQid(); // 每一道题编号
				for (int j = 0; j < stuAnsList.size(); j++) { // 所有学生答案
					String[] str1 = stuAnsList.get(j); // 每一道题学生答案 ，编号，题目类型,答案，
					if (str1.length == 3) { // 如果学生作答了
						if (qid.equals(str1[0])) {
							questions.setSans(str1[2]); // 设置学生答案
						}
					} else if (str1.length == 2) { // 如果学生没作答
						if (qid.equals(str1[0])) {
							questions.setSans("抱歉，此题您未作答");
						}
					}

				}
			}
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, list);
			try {
				response.sendRedirect("front/page/anspaper.jsp");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 试卷详情
	 * 
	 * @param request
	 * @param response
	 */
	private void detailsExamPaper(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		String eid = request.getParameter("eid"); // 获取试卷编号
		// 根据试卷编号，获取试卷信息
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		ExamPaper examPaper = examPaperBiz.findExamPaperByEid(eid);
		if (examPaper == null) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('暂无考试信息...');window.close();</script>");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			HttpSession session = request.getSession();
			String subjects = examPaper.getSubjects(); // 获取所有试题信息
			subjects = subjects.replaceAll("-[A-Z0-9a-z\\u4e00-\\u9fa5]{1,}-[\\d]", "");
			// 得到这套试卷的每道题的编号 S_3,S_11,S_5，S_7,S_9
			// 添加单引号
			subjects = "'" + subjects.replace(",", "','") + "'";
			// 得到 ‘S_3’,‘S_11’,‘S_5’，‘S_7’,‘S_9’
			IQuestionsBiz questionBiz = new QuestionsBizImpl();
			List<Questions> list = questionBiz.findByQids(subjects);
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, list);

			try {
				response.sendRedirect("back/manager/page/exam.jsp");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 参加考试，准备考试试卷信息
	 * 
	 * @param request
	 * @param response
	 */
	private void joinExam(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		String eid = request.getParameter("eid"); // 获取试卷编号
		// 根据试卷编号，获取试卷信息
		IExamPaperBiz examPaperBiz = new ExamPaperBizImpl();
		ExamPaper examPaper = examPaperBiz.findExamPaperByEid(eid);
		if (examPaper == null) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('暂无考试信息...');window.history.go(-1);</script>");
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute(SessionAttributeKey.EXAMPAPERINFO, examPaper);
			String subjects = examPaper.getSubjects(); // 获取所有试题信息
			subjects = subjects.replaceAll("-[A-Z0-9a-z\\u4e00-\\u9fa5]{1,}-[\\d]", "");
			// 得到这套试卷的每道题的编号 S_3,S_11,S_5，S_7,S_9
			// 添加单引号
			subjects = "'" + subjects.replace(",", "','") + "'";
			// 得到 ‘S_3’,‘S_11’,‘S_5’，‘S_7’,‘S_9’
			IQuestionsBiz questionBiz = new QuestionsBizImpl();
			List<Questions> list = questionBiz.findByQids(subjects);
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, list);

			try {
				response.sendRedirect("front/page/examming.jsp");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
