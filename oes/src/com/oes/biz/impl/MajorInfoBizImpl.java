package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.MajorInfo;
import com.oes.biz.IMajorInfoBiz;
import com.oes.dao.IMajorInfoDao;
import com.oes.dao.impl.MajorInfoDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class MajorInfoBizImpl implements IMajorInfoBiz {
	IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();

	@Override
	public List<MajorInfo> finds() {
		// TODO Auto-generated method stub
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		return majorInfoDao.finds();
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		return majorInfoDao.getTotal();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		int total = majorInfoDao.getTotal();
		List<MajorInfo> list = majorInfoDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int addMajorInfo(MajorInfo majorInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(majorInfo.getMname())) {
			return -1;
		} else {
			IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
			return majorInfoDao.addMajorInfo(majorInfo);
		}
	}

	@Override
	public int updateMajorInfo(MajorInfo majorInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(majorInfo)) {
			return -1;
		} else {
			IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
			return majorInfoDao.updateMajorInfo(majorInfo);
		}
	}

	@Override
	public int updateMajorInfo(String mids, int status) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(mids)) {
			return -1;
		} else {
			IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
			return majorInfoDao.updateMajorInfo(mids, status);
		}
	}

}
