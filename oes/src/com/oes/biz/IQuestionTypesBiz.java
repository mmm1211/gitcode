package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.QuestionTypes;

public interface IQuestionTypesBiz {

	/**
	 * 获取所有题目类型信息
	 * 
	 * @return
	 */
	public List<QuestionTypes> findAll();

	/**
	 * 分页查询
	 * 
	 * 
	 */
	public Map<String, Object> findByPages(int pageNo, int pageSize);

	/**
	 * 条件查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param tid
	 * @param tname
	 * @return
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize, String tid, String tname);

}
