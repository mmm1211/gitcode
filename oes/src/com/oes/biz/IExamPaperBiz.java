package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.ExamPaper;

public interface IExamPaperBiz {

	public Map<String, Object> getInfos();

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
	public Map<String, Object> findByPage(int pageNo, int pageSize);

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

	/**
	 * 条件查询
	 * 
	 * @param cid
	 * @param semester
	 * @param courseId
	 * @param mid
	 * @param grade
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findByCondition(String cid, String cids, String status, int pageNo, int pageSize);

}
