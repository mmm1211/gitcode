package com.oes.biz;

import java.util.List;
import java.util.Map;

import com.oes.bean.MajorInfo;

public interface IMajorInfoBiz {

	/**
	 * 获取所有专业信息
	 * 
	 * @return
	 */
	public List<MajorInfo> finds();

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
	 * 添加专业信息
	 * 
	 * @param MajorInfo
	 * @return
	 */
	public int addMajorInfo(MajorInfo majorInfo);

	/**
	 * 修改专业信息
	 * 
	 * @param MajorInfo
	 * @return
	 */
	public int updateMajorInfo(MajorInfo majorInfo);

	/**
	 * 修改专业状态
	 * 
	 * @param MajorInfo
	 * @return
	 */
	public int updateMajorInfo(String mids, int status);

}
