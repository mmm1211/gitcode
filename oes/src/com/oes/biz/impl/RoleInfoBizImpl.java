package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.RoleInfo;
import com.oes.biz.IRoleInfoBiz;
import com.oes.dao.IRoleInfoDao;
import com.oes.dao.impl.RoleInfoDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class RoleInfoBizImpl implements IRoleInfoBiz {

	@Override
	public List<RoleInfo> finds() {
		// TODO Auto-generated method stub
		IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
		return roleInfoDao.finds();
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
		return roleInfoDao.getTotal();
	}

	@Override
	public Map<String, Object> findByPages(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
		int total = roleInfoDao.getTotal();
		List<RoleInfo> list = roleInfoDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int addRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(roleInfo)) {
			return -1;
		} else {
			IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
			return roleInfoDao.addRoleInfo(roleInfo);
		}
	}

	@Override
	public int updateRoleInfo(RoleInfo roleInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(roleInfo)) {
			return -1;
		} else {
			IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
			return roleInfoDao.updateRoleInfo(roleInfo);
		}
	}

	@Override
	public int updateRoleInfo(String rids, String status) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(rids, status)) {
			return -1;
		} else {
			IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
			return roleInfoDao.updateRoleInfo(rids, status);
		}
	}

	@Override
	public RoleInfo findByRid(String rid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(rid)) {
			return null;
		} else {
			IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
			return roleInfoDao.findByRid(rid);
		}
	}
}
