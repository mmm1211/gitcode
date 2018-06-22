package com.oes.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String currentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		String currentTime = sdf.format(new Date());
		return currentTime;
	}
}
