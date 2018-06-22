package com.oes.dao;

import java.util.List;

import com.oes.bean.QuestionTypes;

public interface IQuestionTypesDao {

	/**
	 * 获取所有题目类型信息
	 * 
	 * @return
	 */
	public List<QuestionTypes> findAll();

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<QuestionTypes> findByPage(int pageNo, int pageSize);

	/**
	 * 根据条件查询记录数
	 * 
	 * @param tid
	 * @param tname
	 * @return
	 */
	public int getTotal(String tid, String tname);

	/**
	 * 条件分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param tid
	 * @param tname
	 * @return
	 */
	public List<QuestionTypes> findByPage(int pageNo, int pageSize, String tid, String tname);

}
