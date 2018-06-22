package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.AdminInfo;
import com.oes.biz.IAdminInfoBiz;
import com.oes.dao.IAdminInfoDao;
import com.oes.dao.impl.AdminInfoDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class AdminInfoBizImpl implements IAdminInfoBiz {

	@Override
	public List<AdminInfo> finds() {
		// TODO Auto-generated method stub
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.finds();
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.getTotal();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		int total = adminInfoDao.getTotal();
		List<AdminInfo> list = adminInfoDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int addAdminInfo(AdminInfo adminInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(adminInfo)) {
			return -1;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			return adminInfoDao.addAdminInfo(adminInfo);
		}
	}

	@Override
	public int updateAdminInfo(AdminInfo adminInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(adminInfo)) {
			return -1;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			return adminInfoDao.updateAdminInfo(adminInfo);
		}
	}

	@Override
	public int updateAdminInfo(String aids, String status) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (StringUtil.isNull(aids, status)) {
			return -1;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			return adminInfoDao.updateAdminInfo(aids, status);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize, String rid, String aname) {
		// TODO Auto-generated method stub
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		int total = adminInfoDao.getTotal(rid, aname);
		List<AdminInfo> list = adminInfoDao.findByPage(pageNo, pageSize, rid, aname);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public AdminInfo findByAid(String aid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(aid)) {
			return null;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			return adminInfoDao.findByAid(aid);
		}
	}

	@Override
	public AdminInfo login(String account, String pwd) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(account, pwd)) {
			return null;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			return adminInfoDao.login(account, pwd);
		}
	}

}
