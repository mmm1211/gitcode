package com.oes.dao;

import java.util.List;

import com.oes.bean.AnsPaper;
import com.oes.bean.StuInfo;

public interface IAnsPaperDao {
	/**
	 * 所有答卷信息
	 * 
	 * @return
	 */
	public List<AnsPaper> finds();

	/**
	 * 添加答卷信息
	 * 
	 * @param AnsPaper
	 * @return
	 */
	public int addAnsPaper(AnsPaper snsPaper);

	/**
	 * 修改答卷信息
	 * 
	 * @param AnsPaper
	 * @return
	 */
	public int updateAnsPaper(AnsPaper snsPaper);

	/**
	 * 根据学生编号编号查询指定学生考试信息
	 * 
	 * @param sid
	 * @return
	 */
	public AnsPaper findBySid(String sid);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AnsPaper> findByPage(int pageNo, int pageSize);

	/**
	 * 多条件组合分页查询
	 * 
	 * @param sid
	 * @param eid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AnsPaper> findByCondition(String eid, String sid, int pageNo, int pageSize);

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 根据指定条件获取总记录数
	 * 
	 * @param sid
	 * @param eid
	 * 
	 * @return
	 */
	public int getTotal(String eid, String sid);

	/**
	 * 删除答卷
	 * 
	 * @param aid
	 * @return
	 */
	public int deleteAnsPaper(String aid);

	/**
	 * 根据答卷编号查询答卷信息
	 * 
	 * @param aid
	 * @return
	 */
	public AnsPaper findByAid(String aid);

	/**
	 * 根据试卷编号查询所有学生成绩信息
	 * 
	 * @param eid
	 * @return
	 */
	public List<AnsPaper> findByEid(String eid);

	/**
	 * 查看所有学生考试信息
	 * 
	 * @return
	 */
	public List<StuInfo> findGrades(int pageNo, int pageSize);

	/**
	 * 查询考试总记录数
	 * 
	 * @return
	 */
	public int getTotalGrade();

	/**
	 * 根据学生编号，试卷编号查询答卷
	 * 
	 * @param sid
	 * @param eid
	 */
	public AnsPaper findBySidAndEid(String sid, String eid);


	/**
	 * 查询某个班级所有学生考试信息
	 * 
	 */
	public List<AnsPaper> findByCidAndEid(String cid,String eid);

	/**
	 * 班级或者课程条件查询
	 * @param cid
	 * @param courseId
	 * @return
	 */
	public List<AnsPaper> findByCid(String cid,int pageNo, int pageSize);
	/**
	 * 班级或者课程条件查询记录数
	 * @param cid
	 * @param courseId
	 * @return
	 */
	public int getTotalCid(String cid);

}
