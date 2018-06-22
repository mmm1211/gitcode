package com.oes.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class UploadUtil {

	//public static String uploadPath = "uploadFile";
	public static String uploadPath = "stuPhotos";

	@SuppressWarnings("unchecked")
	public Map<String, String> upload(PageContext pageContext) {
		// 实例化
		SmartUpload su = new SmartUpload();
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 初始化
			su.initialize(pageContext);

			// 设置上传的参数
			// 编码集
			su.setCharset("utf-8");
			// 设置允许上传的文件列表
			su.setAllowedFilesList("jpg,png,gif");
			// 设置允许上传的单个文件最大大小
			su.setMaxFileSize(2 * 1024 * 1024);
			// 设置一次允许上传的总文件大小
			su.setTotalMaxFileSize(100 * 1024 * 1024);

			// 开始上传
			su.upload();

			// 从smartupload对象中获取我们需要的参数
			Request req = su.getRequest();

			// 从请求中获取普通表单元素的信息，即type不是file的
			Enumeration<String> nums = req.getParameterNames();
			// 循环取出所有的表单元素名即文本框的name属性的属性值
			String name = null;
			while (nums.hasMoreElements()) {
				name = nums.nextElement();
				map.put(name, req.getParameter(name));
			}

			// 取请求中的文件
			Files files = su.getFiles();
			String path = "";
			if (files != null && files.getCount() > 0) {
				File fl = null; // 注意，这个file也是smarlupload中的，而不是io中的
				String basePath = pageContext.getServletContext().getRealPath("/") + "../"; // 获取服务器的绝对路径
				for (int i = 0, len = files.getCount(); i < len; i++) {
					fl = files.getFile(i);

					// 如果使用图片的原名称容易因为图片同名而造成覆盖，所以一般情况下要求重命名文件
					//path = uploadPath + "/" + new Date().getTime() + "_" + fl.getFileName();
					path = uploadPath + "/" + fl.getFileName();

					// 如果将上传的文件放在当前项目下，那么当项目重新部署时，以前上传的文件会丢失，所以一般情况我们将上传的文件放在webapps下，与项目统同级。注意先必须在webapps下创建好此目录

					// 将文件从服务器内存写入到服务器所在的磁盘
					fl.saveAs(basePath + path);
				}
				if (fl != null) {
					map.put(fl.getFieldName(), path);
				}
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return map;
	}
}
