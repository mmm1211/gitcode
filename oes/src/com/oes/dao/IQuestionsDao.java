package com.oes.dao;

import java.util.List;
import java.util.Map;

import com.oes.bean.Questions;

public interface IQuestionsDao {

	/**
	 * 单个添加
	 * 
	 * @param Questions
	 * @return
	 */
	public int addQuestions(Questions questions);

	/**
	 * 批量添加
	 * 
	 * @param Questions
	 * @return
	 */
	public int addQuestions(List<List<String>> list);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Questions> findByPage(int pageNo, int pageSize);

	/**
	 * 根据题目类型，所属课程。题目名称组合查询
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Questions> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize);

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 根据题目类型，所属课程。题目名称组合查询统计
	 */

	public int getTotal(String tid, String cid, String qname);


	/**
	 * 根据课程id获取该课程每种题型的题目数量
	 * 
	 * @param cid
	 * @return
	 */
	public List<Map<String, String>> getTotals(String cid);

	/**
	 * 根据题目编号查看题目信息
	 * 
	 * @param qid
	 * @return
	 */
	public Questions findByQid(String qid);

	/**
	 * 修改
	 * 
	 * @param Questions
	 * @return
	 */
	public int updateQuestions(Questions questions);

	/**
	 * 根据试题编号，查询所有试题信息
	 * 
	 * @param qids
	 * @return
	 */
	public List<Questions> findByQids(String qids);

	/**
	 * 修改题目状态
	 * 
	 * @param qids
	 * @param status
	 * @return
	 */
	public int updateQuestion(String qids, String status);

}
