package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.ClassInfo;

public interface IClassInfoBiz {

	/**
	 * 获取所有班级信息
	 * 
	 * @return
	 */
	public List<ClassInfo> finds();

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 分页查询
	 * 
	 * 
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize);

	/**
	 * 多条件分页查询
	 * 
	 * 
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize, String grade, String mid, String length);

	/**
	 * 添加
	 * 
	 * @param ClassInfo
	 * @return
	 */
	public int addClassInfo(ClassInfo classInfo);

	/**
	 * 修改
	 * 
	 * @param ClassInfo
	 * @return
	 */
	public int updateClassInfo(ClassInfo classInfo);

	/**
	 * 修改班级状态
	 * 
	 * @param ClassInfo
	 * @return
	 */
	public int updateClassInfo(String cids, int status);

	/**
	 * 根据编号查询
	 * 
	 * @param cid
	 * @return
	 */
	public ClassInfo findByCid(String cid);

	/**
	 * 通过专业编号查询班级信息
	 * @param mid
	 * @return
	 */
	public List<ClassInfo>  findByMid(String mid);

}
