package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.AnsPaper;

public interface IAnsPaperBiz {

	/**
	 * 获取所有答卷信息
	 * 
	 * @return
	 */
	public List<AnsPaper> finds();

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 分页查询
	 * 
	 * 
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize);

	/**
	 * 添加
	 * 
	 * @param AnsPaper
	 * @return
	 */
	public int addAnsPaper(AnsPaper AnsPaper);

	/**
	 * 修改
	 * 
	 * @param AnsPaper
	 * @return
	 */
	public int updateAnsPaper(AnsPaper AnsPaper);

	/**
	 * 分页查询时查询指定页指定列的信息
	 * 
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Map
	 */
	public Map<String, Object> findByCondition(String eid, String sid, int pageNo, int pageSize);

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
	 * 根据学生编号编号查询指定学生考试信息
	 * 
	 * @param sid
	 * @return
	 */
	public AnsPaper findBySid(String sid);

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
	public Map<String, Object> findGrades(int pageNo, int pageSize);

	/**
	 * 判断是否已经考过该套试卷了
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
	public Map<String, Object>  findByCid(String cid,int pageNo, int pageSize);

}
