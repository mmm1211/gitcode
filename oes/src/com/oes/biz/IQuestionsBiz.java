package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.Questions;

public interface IQuestionsBiz {

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
	public Map<String, Object> findByPage(int pageNo, int pageSize);

	/**
	 * 根据题目类型，所属课程。题目名称组合查询
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize);

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

	/**
	 * 根据课程id获取该课程每种题型的题目数量
	 * 
	 * @return
	 */
	public Map<String, Object> getInfos();
}
