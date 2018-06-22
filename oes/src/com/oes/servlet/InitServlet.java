package com.oes.servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.oes.util.FileUploadUtil;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path = "files";
	private String photoPath = "photos";

	public void init(ServletConfig config) throws ServletException {
		String temp = config.getInitParameter("path");
		if (temp != null) {
			path = temp;
		}
		// 创建这个路径
		String basePath = config.getServletContext().getRealPath("");
		File file = new File(basePath);
		File fl = new File(file.getParent(), path);
		if (!fl.exists()) {
			fl.mkdirs();
		}
		FileUploadUtil.PATH = "../" + path;
		temp = config.getInitParameter("photoPath");
		if (temp != null) {
			photoPath = temp;
		}
		fl = new File(file.getParent(), photoPath);
		if (!fl.exists()) {
			fl.mkdirs();
		}
		FileUploadUtil.PHOTOPATH = "../" + photoPath;
	}

}
