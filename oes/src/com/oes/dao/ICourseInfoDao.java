package com.oes.dao;

import java.util.List;
import java.util.Map;

import com.oes.bean.CourseInfo;

public interface ICourseInfoDao {

	/**
	 * 获取所有课程信息
	 * 
	 * @return
	 */
	public List<CourseInfo> finds();

	/**
	 * 根据课程名称，学期，或者状态查询课程信息
	 */
	public List<CourseInfo> find(String cname, String semester, Integer status);

	/**
	 * 添加
	 * 
	 * @param CourseInfo
	 * @return
	 */
	public int addCourseInfo(CourseInfo courseInfo);

	/**
	 * 修改课程信息
	 * 
	 * @param CourseInfo
	 * @return
	 */
	public int updateCourseInfo(CourseInfo courseInfo);

	/**
	 * 修改课程状态
	 */
	public int updateCourseInfo(String cids, Integer status);

	/**
	 * 获取商品记录数
	 * 
	 * @param map
	 * @return
	 */
	public int getTotal(Map<String, String> map);

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 根据课程名称，学期，或者状态获取总记录数
	 * 
	 * @return
	 */
	public int getTotal(String cname, int semester, Integer status);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<CourseInfo> findByPage(int pageNo, int pageSize);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<CourseInfo> findByPage(int pageNo, int pageSize, String cname, int semester, Integer status);

}
