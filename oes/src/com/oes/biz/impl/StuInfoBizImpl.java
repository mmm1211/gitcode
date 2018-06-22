package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.ClassInfo;
import com.oes.bean.StuInfo;
import com.oes.biz.IStuInfoBiz;
import com.oes.dao.IStuInfoDao;
import com.oes.dao.impl.StuInfoDaoImpl;
import com.oes.util.MD5Encryption;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class StuInfoBizImpl implements IStuInfoBiz {

	@Override
	public List<StuInfo> finds() {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		return stuInfoDao.finds();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		int total = stuInfoDao.getTotal();
		List<StuInfo> list = stuInfoDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int addStuInfo(StuInfo stuInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(stuInfo)) {
			return -1;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			return stuInfoDao.addStuInfo(stuInfo);
		}
	}

	@Override
	public int updateStuInfo(StuInfo stuInfo) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(stuInfo)) {
			return -1;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			return stuInfoDao.updateStuInfo(stuInfo);
		}
	}

	@Override
	public int updateStuInfo(String sids, int status) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(sids)) {
			return -1;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			return stuInfoDao.updateStuInfo(sids, status);
		}
	}

	@Override
	public List<StuInfo> find(String sid, String sname, String cid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(sid)) {
			return null;
		} else if (StringUtil.isNull(sname)) {
			return null;
		} else if (StringUtil.isNull(cid)) {
			return null;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			return stuInfoDao.find(sid, sname, cid);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize, String sid, String sname, String cid) {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		int total = stuInfoDao.getTotal(sid, sname, cid);
		List<StuInfo> list = stuInfoDao.findByPage(pageNo, pageSize, sid, sname, cid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public List<ClassInfo> getInfo() {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		return stuInfoDao.getInfo();
	}

	@Override
	public int addStuInfos(List<List<String>> list) {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		for (List<String> lt : list) {
			lt.add(2, com.oes.util.MD5Encryption.createPassword(lt.get(4).substring(12)));
			lt.add(5, lt.get(0) + ".jpg");
		}
		return stuInfoDao.addStuInfos(list);
	}

	@Override
	public int addStuInfo(String sid, String sname, String cid, String sex, String cardID, String tel) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(sid, sname, cid, sex, cardID)) {
			return -1;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			String pwd = MD5Encryption.createPassword(cardID.substring(12));
			String photo = sid + ".jpg";
			return stuInfoDao.addStuInfo(sid, sname, pwd, cid, sex, photo, cardID, tel);
		}
	}

	@Override
	public Map<String, Object> findByCondition(String sid, String sname, String mid, String cid, String grade,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		int total = stuInfoDao.getTotal(sid, sname, mid, cid, grade, pageNo, pageSize);
		List<StuInfo> list = stuInfoDao.findByCondition(sid, sname, mid, cid, grade, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public int resetPwd(String sid, String pwd) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(sid, pwd)) {
			return -1;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			pwd = MD5Encryption.createPassword(pwd);
			return stuInfoDao.resetPwd(sid, pwd);
		}
	}

	@Override
	public StuInfo findBySid(String sid) {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		return stuInfoDao.findBySid(sid);

	}

	@Override
	public int updatePhoto(String sid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(sid)) {
			return -1;
		} else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			return stuInfoDao.updatePhoto(sid);

		}
	}

	@Override
	public StuInfo login(String account, String pwd) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(account, pwd)) {
			return null;
		} else {
			pwd = MD5Encryption.createPassword(pwd);
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			return stuInfoDao.login(account, pwd);

		}
	}

	@Override
	public Map<String, Object> findGadeBySid(int pageNo, int pageSize, String sid) {
		// TODO Auto-generated method stub
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		int total = stuInfoDao.getTotal(sid);
		List<StuInfo> list = stuInfoDao.findGadeBySid(pageNo, pageSize, sid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

}
