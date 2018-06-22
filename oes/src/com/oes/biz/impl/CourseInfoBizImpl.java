package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.CourseInfo;
import com.oes.biz.ICourseInfoBiz;
import com.oes.dao.ICourseInfoDao;
import com.oes.dao.impl.CourseInfoDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class CourseInfoBizImpl implements ICourseInfoBiz {

	@Override
	public List<CourseInfo> finds() {
		// TODO Auto-generated method stub
		ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
		return courseInfoDao.finds();
	}

	@Override
	public int addCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(courseInfo)) {
			return -1;
		} else {
			ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
			return courseInfoDao.addCourseInfo(courseInfo);
		}
	}

	public int updateCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(courseInfo)) {
			return -1;
		} else {
			ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
			return courseInfoDao.updateCourseInfo(courseInfo);
		}
	}

	@Override
	public int updateCourseInfo(String cids, Integer status) {
		// TODO Auto-generated method stub
		if (!StringUtil.isNull(cids)) {
			ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
			return courseInfoDao.updateCourseInfo(cids, status);
		} else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
		int total = courseInfoDao.getTotal();
		List<CourseInfo> list = courseInfoDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize, String cname, int semester, Integer status) {
		// TODO Auto-generated method stub
		ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
		int total = courseInfoDao.getTotal(cname, semester, status);
		List<CourseInfo> list = courseInfoDao.findByPage(pageNo, pageSize, cname, semester, status);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

}
