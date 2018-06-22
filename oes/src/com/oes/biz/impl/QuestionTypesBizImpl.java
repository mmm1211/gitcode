package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.QuestionTypes;
import com.oes.biz.IQuestionTypesBiz;
import com.oes.dao.IQuestionTypesDao;
import com.oes.dao.impl.QuestionTypesDaoImpl;

public class QuestionTypesBizImpl implements IQuestionTypesBiz {
	IQuestionTypesDao questionTypesDao = new QuestionTypesDaoImpl();

	@Override
	public List<QuestionTypes> findAll() {
		// TODO Auto-generated method stub
		IQuestionTypesDao questionTypesDao = new QuestionTypesDaoImpl();
		return questionTypesDao.findAll();
	}

	@Override
	public Map<String, Object> findByPages(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IQuestionTypesDao questionTypesDao = new QuestionTypesDaoImpl();
		int total = questionTypesDao.getTotal();
		List<QuestionTypes> list = questionTypesDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize, String tid, String tname) {
		// TODO Auto-generated method stub
		IQuestionTypesDao questionTypesDao = new QuestionTypesDaoImpl();
		int total = questionTypesDao.getTotal(tid, tname);
		List<QuestionTypes> list = questionTypesDao.findByPage(pageNo, pageSize, tid, tname);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

}
