package com.oes.util;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

public class FileUploadUtil {

	public static String PATH = "files"; // 文件上传后存储在服务器的哪个路径下
	public static String PHOTOPATH = "photos"; // 文件上传后存储在服务器的哪个路径下
	private static final String ALLOWED = "gif,jpg,png,jepg,doc,txt,xls,xlsx";// 允许上传的文件列表
	private static final String DENIED = "exe,bat,jsp,hhtml";// 拒绝上传的文件列表
	private static final int SINGLEFILESIZE = 1024 * 1024 * 10;// 每个文件的最大大小
	private static final int TOTALFILESIZE = 100 * 1024 * 1024;// 每次上传的最大大小

	@SuppressWarnings("unchecked")
	public Map<String, String> upload(PageContext pageContext) {
		Map<String, String> map = new HashMap<String, String>();
		SmartUpload su = new SmartUpload();
		try {
			// 初始化
			su.initialize(pageContext);
			// 设置上传的参数
			// 编码集
			su.setCharset("utf-8");
			// 允许上传的格式
			su.setAllowedFilesList(ALLOWED);
			// 拒绝上传的格式
			su.setDeniedFilesList(DENIED);
			// 设置允许上传的单个文件最大大小
			su.setMaxFileSize(SINGLEFILESIZE);
			// 设置一次允许上传的总文件大小
			su.setTotalMaxFileSize(TOTALFILESIZE);

			// 开始上传
			su.upload();

			// 从smartupload对象中获取我们需要的参数
			Request req = su.getRequest();

			// 从请求中获取普通表单元素的信息，即type不是file的
			Enumeration<String> enums = req.getParameterNames();
			// 循环取出所有的表单元素名即文本框的name属性的属性值
			String fieldName = null;
			while (enums.hasMoreElements()) {
				fieldName = String.valueOf(enums.nextElement());
				map.put(fieldName, req.getParameter(fieldName));
			}
			// 取请求中的文件

			if (su.getFiles() != null && su.getFiles().getCount() > 0) { // 说明有文件要上传
				Files fs = su.getFiles();
				Collection<File> files = fs.getCollection();
				String path = null;
				String filePath = "";
				String fName = "";
				String temp = null;
				for (File fl : files) { // 循环所有的文件
					if (!fl.isMissing()) {
						fName = fl.getFieldName();
						if (temp == null) {
							temp = fName;
						} else {
							if (!temp.equals(fName)) {
								map.put(temp, filePath);
								filePath = "";
								temp = fName;
							}
						}
						path = PATH + "/" + new Date().getTime() + "_" + fl.getFileName();
						System.out.println("path " + path);

						// 上传文件
						fl.saveAs(pageContext.getServletContext().getRealPath("/") + path);
						System.out.println("getRealPath " + path);
						if (filePath != "") {
							filePath += "";
						}
						filePath += path;
					}
				}
				map.put(fName, filePath);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public String uploadExcelFile(PageContext pageContext) {
		SmartUpload su = new SmartUpload();
		String filePath = "";
		try {
			// 初始化
			su.initialize(pageContext);
			// 设置上传的参数
			// 编码集
			su.setCharset("utf-8");
			// 允许上传的格式
			su.setAllowedFilesList("xls,xlsx");
			// 拒绝上传的格式
			su.setDeniedFilesList(DENIED);
			// 设置允许上传的单个文件最大大小
			su.setMaxFileSize(SINGLEFILESIZE);
			// 设置一次允许上传的总文件大小
			su.setTotalMaxFileSize(TOTALFILESIZE);

			// 开始上传
			su.upload();
			// 从smartupload请求中获取普通文本参数text,number,password 即非file
			if (su.getFiles() != null && su.getFiles().getCount() > 0) { // 说明有文件要上传
				Files fs = su.getFiles();
				Collection<File> files = fs.getCollection();
				for (File fl : files) { // 循环所有的文件
					if (!fl.isMissing()) {

						filePath = PATH + "/" + new Date().getTime() + "_" + fl.getFileName();
						// 上传文件
						fl.saveAs(pageContext.getServletContext().getRealPath("/") + filePath);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

	@SuppressWarnings("unchecked")
	public int uploadPhoto(PageContext pageContext) {
		SmartUpload su = new SmartUpload();
		String filePath = "";
		try {
			// 初始化
			su.initialize(pageContext);
			// 设置上传的参数
			// 编码集
			su.setCharset("utf-8");
			// 允许上传的格式
			su.setAllowedFilesList("jpg,png,jpeg");
			// 拒绝上传的格式
			su.setDeniedFilesList(DENIED);
			// 设置允许上传的单个文件最大大小
			su.setMaxFileSize(SINGLEFILESIZE);
			// 设置一次允许上传的总文件大小
			su.setTotalMaxFileSize(TOTALFILESIZE);

			// 开始上传
			su.upload();
			// 从smartupload请求中获取普通文本参数text,number,password 即非file
			if (su.getFiles() != null && su.getFiles().getCount() > 0) { // 说明有文件要上传
				Files fs = su.getFiles();
				Collection<File> files = fs.getCollection();
				for (File fl : files) { // 循环所有的文件
					if (!fl.isMissing()) {
						// filePath = PHOTOPATH + "/" + new Date().getTime() +
						// "_" + fl.getFileName();
						filePath = PHOTOPATH + "/" + fl.getFileName();
						// 上传文件
						fl.saveAs(pageContext.getServletContext().getRealPath("/") + filePath);
						
					}
				}
				return 1;
			} else {
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
