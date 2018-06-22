package com.oes.dao;

import java.util.List;

import com.oes.bean.ClassInfo;

public interface IClassInfoDao {

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
	 * @param cids
	 * @return
	 */
	public int updateClassInfo(String cids, int status);

	/**
	 * 获取所有班级信息
	 * 
	 * @return
	 */
	public List<ClassInfo> finds();

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ClassInfo> findByPage(int pageNo, int pageSize);

	/**
	 * 多条件查询班级信息
	 * 
	 * @return
	 */
	public List<ClassInfo> find(String grade, String mid, String length);

	/**
	 * 多条件组合分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ClassInfo> findByPage(int pageNo, int pageSize, String grade, String mid, String length);

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotal();

	/**
	 * 多条件统计班级数量
	 * 
	 * @return
	 */
	public int getTotal(String grade, String mid, String length);

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
