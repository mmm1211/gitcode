package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.ClassInfo;
import com.oes.bean.CourseInfo;
import com.oes.bean.ExamPaper;
import com.oes.bean.MajorInfo;
import com.oes.biz.IExamPaperBiz;
import com.oes.dao.IClassInfoDao;
import com.oes.dao.ICourseInfoDao;
import com.oes.dao.IExamPaperDao;
import com.oes.dao.IMajorInfoDao;
import com.oes.dao.impl.ClassInfoDaoImpl;
import com.oes.dao.impl.CourseInfoDaoImpl;
import com.oes.dao.impl.ExamPaperDaoImpl;
import com.oes.dao.impl.MajorInfoDaoImpl;

public class ExamPaperBizImpl implements IExamPaperBiz {

	public Map<String, Object> getInfos() {
		// 获取专业信息
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		List<MajorInfo> majors = majorInfoDao.finds();
		// 获取班级信息
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		List<ClassInfo> classInfos = classInfoDao.finds();
		// 获取课程信息
		ICourseInfoDao courseInfoDao = new CourseInfoDaoImpl();
		List<CourseInfo> courseInfos = courseInfoDao.finds();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("majors", majors);
		map.put("classInfos", classInfos);
		map.put("courseInfos", courseInfos);
		return map;

	}

	@Override
	public List<Map<String, String>> findQuestionByCid(String cid) {
		// TODO Auto-generated method stub
		if (com.oes.util.StringUtil.isNull(cid)) {
			return null;
		} else {
			IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
			return examPaperDao.findQuestionByCid(cid);
		}
	}

	@Override
	public int addExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
		return examPaperDao.addExamPaper(examPaper);
	}

	@Override
	public int updateExamPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
		return examPaperDao.updateExamPaper(examPaper);
	}

	@Override
	public ExamPaper findExamPaperByEid(String eid) {
		// TODO Auto-generated method stub
		if (com.oes.util.StringUtil.isNull(eid)) {
			return null;
		} else {
			IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
			return examPaperDao.findExamPaperByEid(eid);
		}
	}

	@Override
	public List<ExamPaper> findByCid(String cid) {
		// TODO Auto-generated method stub
		if (com.oes.util.StringUtil.isNull(cid)) {
			return null;
		} else {
			IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
			return examPaperDao.findByCid(cid);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
		int total = examPaperDao.getTotal();
		List<ExamPaper> list = examPaperDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int updateExamPaper(String eid, String status) {
		// TODO Auto-generated method stub
		if (com.oes.util.StringUtil.isNull(eid, status)) {
			return -1;
		} else {
			IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
			return examPaperDao.updateExamPaper(eid, status);
		}
	}

	@Override
	public int deleteExamPaper(String eid) {
		// TODO Auto-generated method stub
		if (com.oes.util.StringUtil.isNull(eid)) {
			return -1;
		} else {
			IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
			return examPaperDao.deleteExamPaper(eid);
		}
	}

	@Override
	public Map<String, Object> findByCondition(String cid, String cids, String status, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
		int total = examPaperDao.getTotal(cid, cids, status);
		List<ExamPaper> list = examPaperDao.findByCondition(cid, cids, status, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public List<ExamPaper> finds() {
		// TODO Auto-generated method stub
		IExamPaperDao examPaperDao = new ExamPaperDaoImpl();
		return examPaperDao.finds();
	}
}
