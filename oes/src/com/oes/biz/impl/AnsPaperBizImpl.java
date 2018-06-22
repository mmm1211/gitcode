package com.oes.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oes.bean.AnsPaper;
import com.oes.bean.StuInfo;
import com.oes.biz.IAnsPaperBiz;
import com.oes.dao.IAnsPaperDao;
import com.oes.dao.impl.AnsPaperDaoImpl;
import com.oes.util.ObjectUtil;
import com.oes.util.StringUtil;

public class AnsPaperBizImpl implements IAnsPaperBiz {
	@Override
	public List<AnsPaper> finds() {
		// TODO Auto-generated method stub
		IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
		return ansPaperDao.finds();
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
		return ansPaperDao.getTotal();
	}

	@Override
	public int addAnsPaper(AnsPaper AnsPaper) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(AnsPaper)) {
			return -1;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.addAnsPaper(AnsPaper);
		}
	}

	@Override
	public int updateAnsPaper(AnsPaper AnsPaper) {
		// TODO Auto-generated method stub
		if (ObjectUtil.isNull(AnsPaper)) {
			return -1;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.updateAnsPaper(AnsPaper);
		}
	}

	@Override
	public Map<String, Object> findByCondition(String eid, String sid, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
		int total = ansPaperDao.getTotal(eid, sid);
		List<AnsPaper> list = ansPaperDao.findByCondition(eid, sid, pageNo, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	/**
	 * 删除答卷
	 * 
	 * @param aid
	 * @return
	 */
	public int deleteAnsPaper(String aid) {
		if (StringUtil.isNull(aid)) {
			return -1;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.deleteAnsPaper(aid);
		}
	};

	/**
	 * 根据答卷编号查询答卷信息
	 * 
	 * @param aid
	 * @return
	 */
	public AnsPaper findByAid(String aid) {
		if (StringUtil.isNull(aid)) {
			return null;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.findByAid(aid);
		}
	};

	/**
	 * 根据学生编号编号查询指定学生考试信息
	 * 
	 * @param sid
	 * @return
	 */
	public AnsPaper findBySid(String sid) {
		if (StringUtil.isNull(sid)) {
			return null;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.findBySid(sid);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub

		IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
		int total = ansPaperDao.getTotal();
		List<AnsPaper> list = ansPaperDao.findByPage(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	@Override
	public Map<String, Object> findGrades(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
		int total = ansPaperDao.getTotalGrade();
		List<StuInfo> list = ansPaperDao.findGrades(pageNo, pageSize);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", list);
		return result;
	}

	@Override
	public AnsPaper findBySidAndEid(String sid, String eid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(sid, eid)) {
			return null;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.findBySidAndEid(sid, eid);
		}
	}

	@Override
	public List<AnsPaper> findByEid(String eid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(eid)) {
			return null;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.findByEid(eid);
		}
	}

	@Override
	public List<AnsPaper> findByCidAndEid(String cid,String eid) {
		// TODO Auto-generated method stub
		if (StringUtil.isNull(cid,eid)) {
			return null;
		} else {
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			return ansPaperDao.findByCidAndEid(cid,eid);
		}
	}

	@Override
	public Map<String, Object>  findByCid(String cid,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
			
			IAnsPaperDao ansPaperDao = new AnsPaperDaoImpl();
			int total = ansPaperDao.getTotalCid(cid);
			List<AnsPaper> list = ansPaperDao.findByCid(cid,pageNo, pageSize);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", total);
			result.put("rows", list);
			return result;
		
	};

}
