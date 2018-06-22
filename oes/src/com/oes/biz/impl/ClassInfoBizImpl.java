package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.ClassInfo;
import com.oes.biz.IClassInfoBiz;
import com.oes.dao.IClassInfoDao;
import com.oes.dao.impl.ClassInfoDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class ClassInfoBizImpl implements IClassInfoBiz {
	IClassInfoDao classInfoDao = new ClassInfoDaoImpl();

	@Override
	public List<ClassInfo> finds() {
		// TODO Auto-generated method stub
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		return classInfoDao.finds();
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		return classInfoDao.getTotal();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		int total = classInfoDao.getTotal();
		List<ClassInfo> list = classInfoDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize, String grade, String mid, String length) {
		// TODO Auto-generated method stub
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		int total = classInfoDao.getTotal(grade, mid, length);
		List<ClassInfo> list = classInfoDao.findByPage(pageNo, pageSize, grade, mid, length);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int addClassInfo(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(classInfo)) {
			return -1;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.addClassInfo(classInfo);
		}
	}

	@Override
	public int updateClassInfo(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(classInfo)) {
			return -1;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.updateClassInfo(classInfo);
		}
	}

	@Override
	public int updateClassInfo(String cids, int status) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(cids)) {
			return -1;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.updateClassInfo(cids, status);
		}
	}

	@Override
	public ClassInfo findByCid(String cid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(cid)) {
			return null;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.findByCid(cid);
		}
	}

	@Override
	public List<ClassInfo>  findByMid(String mid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(mid)) {
			return null;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.findByMid(mid);
		}
	}

}
