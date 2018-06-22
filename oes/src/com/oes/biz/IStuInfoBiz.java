package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.ClassInfo;
import com.oes.bean.StuInfo;

public interface IStuInfoBiz {
	/**
	 * 获取专业班级信息
	 */
	public List<ClassInfo> getInfo();

	/**
	 * 批量添加学生
	 * 
	 * @param list
	 * @return
	 */
	public int addStuInfos(List<List<String>> list);

	/**
	 * 单个添加学生信息
	 * 
	 * @param sid
	 * @param sname
	 * @param pwd
	 * @param cid
	 * @param sex
	 * @param photo
	 * @param cardID
	 * @param tel
	 * @return
	 */
	public int addStuInfo(String sid, String sname, String cid, String sex, String cardID, String tel);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize);

	/**
	 * 多条件组合分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findByCondition(String sid, String sname, String mid, String cid, String grade,
			int pageNo, int pageSize);

	/**
	 * 修改密码
	 * 
	 * @param sid
	 * @param pwd
	 * @return
	 */
	public int resetPwd(String sid, String pwd);

	/**
	 * 根据学号查看学生信息
	 * 
	 * @param sid
	 */
	public StuInfo findBySid(String sid);

	/**
	 * 修改图片
	 * 
	 * @param sid
	 */
	public int updatePhoto(String sid);

	/**
	 * 添加
	 * 
	 * @param StuInfo
	 * @return
	 */
	public int addStuInfo(StuInfo StuInfo);

	/**
	 * 修改
	 * 
	 * @param StuInfo
	 * @return
	 */
	public int updateStuInfo(StuInfo StuInfo);

	/**
	 * 修改学生状态
	 * 
	 * @param cids
	 * @return
	 */
	public int updateStuInfo(String sids, int status);

	/**
	 * 获取所有学生信息
	 * 
	 * @return
	 */
	public List<StuInfo> finds();

	/**
	 * 多条件查询学生信息
	 * 
	 * @return
	 */
	public List<StuInfo> find(String sid, String sname, String cid);

	/**
	 * 多条件组合分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize, String sid, String sname, String cid);

	/**
	 * 登录的方法
	 * 
	 * @param account
	 * @param pwd
	 * @return
	 */
	public StuInfo login(String account, String pwd);

	/**
	 * 前台查询所有考试信息
	 * 
	 * @param sid
	 * @return
	 */
	public Map<String, Object> findGadeBySid(int pageNo, int pageSize, String sid);

}
