package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.CourseInfo;

public interface ICourseInfoBiz {

	/**
	 * 获取所有课程信息
	 * 
	 * @return
	 */
	public List<CourseInfo> finds();

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
	 * 
	 * @param CourseInfo
	 * @return
	 */
	public int updateCourseInfo(String cids, Integer status);

	/**
	 * 以easyui的格式返回所有课程信息
	 * 
	 * @return
	 * 
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize);

	/**
	 * 以easyui的格式返回所有课程信息
	 *
	 * @param CourseInfo
	 * @return
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize, String cname, int semester, Integer status);

}
