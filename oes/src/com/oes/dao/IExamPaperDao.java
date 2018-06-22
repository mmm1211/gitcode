package com.oes.dao;

import java.util.List;
import java.util.Map;

import com.oes.bean.ExamPaper;

public interface IExamPaperDao {
	/**
	 * 所有试卷信息
	 * 
	 * @return
	 */
	public List<ExamPaper> finds();

	/**
	 * 根据课程编号获取课程的所有题目的题号和正确答案以及题型
	 * 
	 * @param cid
	 * @return
	 */
	public List<Map<String, String>> findQuestionByCid(String cid);

	/**
	 * 添加试卷信息
	 * 
	 * @param ExamPaper
	 * @return
	 */
	public int addExamPaper(ExamPaper examPaper);

	/**
	 * 修改试卷信息
	 * 
	 * @param ExamPaper
	 * @return
	 */
	public int updateExamPaper(ExamPaper examPaper);

	/**
	 * 根据试卷编号查询试卷信息
	 * 
	 * @param eid
	 * 
	 * @return
	 */
	public ExamPaper findExamPaperByEid(String eid);

	/**
	 * 根据课程编号查询该课程下考试信息
	 * 
	 * @param cid
	 * @return
	 */
	public List<ExamPaper> findByCid(String cid);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ExamPaper> findByPage(int pageNo, int pageSize);

	/**
	 * 多条件组合分页查询
	 * 
	 * @param grade
	 * @param mid
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ExamPaper> findByCondition(String cid, String cids, String status, int pageNo, int pageSize);

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 根据指定条件获取总记录数
	 * 
	 * @param grade
	 * @param mid
	 * 
	 * @return
	 */
	public int getTotal(String cid, String cids, String status);

	/**
	 * 修改试卷状态
	 * 
	 * @param eid
	 * @param status
	 * @return
	 */
	public int updateExamPaper(String eid, String status);

	/**
	 * 删除试卷
	 * 
	 * @param eid
	 * @return
	 */
	public int deleteExamPaper(String eid);

}
