package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.CourseInfo;
import com.oes.bean.QuestionTypes;
import com.oes.bean.Questions;
import com.oes.biz.ICourseInfoBiz;
import com.oes.biz.IQuestionTypesBiz;
import com.oes.biz.IQuestionsBiz;
import com.oes.dao.IQuestionsDao;
import com.oes.dao.impl.QuestionsDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class QuestionsBizImpl implements IQuestionsBiz {

	@Override
	public int addQuestions(Questions questions) {
		// TODO Auto-generated method stub
		IQuestionsDao questionsDao = new QuestionsDaoImpl();
		return questionsDao.addQuestions(questions);
	}

	@Override
	public int addQuestions(List<List<String>> list) {
		// TODO Auto-generated method stub
		if (!ObjectUtil.isNull(list)) {

			IQuestionsDao questionsDao = new QuestionsDaoImpl();
			return questionsDao.addQuestions(list);
		} else {
			return -1;
		}

	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IQuestionsDao questionsDao = new QuestionsDaoImpl();
		int total = questionsDao.getTotal();
		List<Questions> list = questionsDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IQuestionsDao questionsDao = new QuestionsDaoImpl();
		int total = questionsDao.getTotal(tid, cid, qname);
		List<Questions> list = questionsDao.findByCondition(tid, cid, qname, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;

	}

	@Override
	public List<Map<String, String>> getTotals(String cid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(cid)) {
			return null;
		} else {
			IQuestionsDao questionsDao = new QuestionsDaoImpl();
			return questionsDao.getTotals(cid);
		}
	}

	@Override
	public Questions findByQid(String qid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(qid)) {
			return null;
		} else {
			IQuestionsDao questionsDao = new QuestionsDaoImpl();
			return questionsDao.findByQid(qid);
		}
	}

	@Override
	public int updateQuestions(Questions questions) {
		// TODO Auto-generated method stub
		IQuestionsDao questionsDao = new QuestionsDaoImpl();
		return questionsDao.updateQuestions(questions);
	}

	@Override
	public List<Questions> findByQids(String qids) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(qids)) {
			return null;
		} else {
			IQuestionsDao questionsDao = new QuestionsDaoImpl();
			return questionsDao.findByQids(qids);
		}
	}

	@Override
	public int updateQuestion(String qids, String status) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(qids, status)) {
			return -1;
		} else {
			IQuestionsDao questionsDao = new QuestionsDaoImpl();
			return questionsDao.updateQuestion(qids, status);
		}
	}

	@Override
	public Map<String, Object> getInfos() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		ICourseInfoBiz courseInfoBiz = new CourseInfoBizImpl();
		List<CourseInfo> cfs = courseInfoBiz.finds();
		IQuestionTypesBiz questionTypes = new QuestionTypesBizImpl();
		List<QuestionTypes> qs = questionTypes.findAll();
		map.put("courseInfos", cfs);
		map.put("questionTypes", qs);
		return map;

	}

}
