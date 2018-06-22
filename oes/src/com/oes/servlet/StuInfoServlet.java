package com.oes.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.oes.bean.StuInfo;
import com.oes.biz.IStuInfoBiz;
import com.oes.biz.impl.StuInfoBizImpl;
import com.oes.util.FileUploadUtil;
import com.oes.util.MD5Encryption;
import com.oes.util.ReadExcelToDB;
import com.oes.util.SessionAttributeKey;
import com.oes.util.StringUtil;
import com.oes.util.UploadUtil;

/**
 * Servlet implementation class StuInfoServlet
 */
@WebServlet("/StuInfoServlet")
public class StuInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StuInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");
		if ("findAll".equals(op)) { // 所有学生信息
			findAll(request, response);
		} else if ("addStuInfo".equals(op)) { // 单个添加学生
			addStuInfo(request, response);
		} else if ("addStudent".equals(op)) { // 单个添加学生/无照片
			addStudent(request, response);
		} else if ("updateStuInfo".equals(op)) { // 修改学生信息
			updateStuInfo(request, response);
		} else if ("findAll".equals(op)) { // 查询所有学生信息
			findAll(request, response);
		} else if ("findByPage".equals(op)) { // 分页查询
			findByPage(request, response);
		} else if ("getInfos".equals(op)) { // 获取专业班级信息
			getInfos(request, response);
		} else if ("addStuInfos".equals(op)) { // 批量添加学生信息
			addStuInfos(request, response);
		} else if ("addStuInfoPhotos".equals(op)) { // 批量添加学生图片
			addStuInfoPhotos(request, response);
		} else if ("resetPwd".equals(op)) { // 批量添加学生图片
			resetPwd(request, response);
		} else if ("findByCondition".equals(op)) { // 条件查询
			findByCondition(request, response);
		} else if ("findBySid".equals(op)) { // 根据编号查找
			findBySid(request, response);
		} else if ("findByFrontSid".equals(op)) { // 前台根据编号查找
			findByFrontSid(request, response);
		} else if ("checkPwd".equals(op)) { // 验证密码
			checkPwd(request, response);
		} else if ("updatePwd".equals(op)) { // 修改密码findGadeBySid
			updatePwd(request, response);
		} else if ("findGadeBySid".equals(op)) { // 查询制定学号学生所有考试记录
			findGadeBySid(request, response);
		} else if ("updateStuInfoWithOutPhoto".equals(op)) { // 不修改图片
			updateStuInfoWithOutPhoto(request, response);
		}
	}

	/**
	 * 不修改图片，修改学生信息
	 * 
	 * @param request
	 * @param response
	 */
	private void updateStuInfoWithOutPhoto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();

		String cid = request.getParameter("cid");
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String sex = request.getParameter("sex");
		String cardID = request.getParameter("cardID");
		String tel = request.getParameter("tel");
		String pwd = request.getParameter("pwd");
		String photo = request.getParameter("sid") + ".jpg";

		StuInfo stuInfo = new StuInfo();
		stuInfo.setCardID(cardID);
		stuInfo.setSid(sid);
		stuInfo.setSname(sname);
		stuInfo.setTel(tel);
		stuInfo.setPhoto(photo);
		stuInfo.setPwd(pwd);
		stuInfo.setCid(Integer.parseInt(cid));
		stuInfo.setSex(sex);
		int result = stuInfoBiz.updateStuInfo(stuInfo);
		this.send(response, result);

	}

	/**
	 * 通过学号查询学生考试信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findGadeBySid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stuInfo.getSid();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.findGadeBySid(pageNo, pageSize, sid));
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 */
	private void updatePwd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stuInfo.getSid();
		String pwd = request.getParameter("pwd");
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.resetPwd(sid, pwd));
	}

	/**
	 * 验证密码
	 * 
	 * @param request
	 * @param response
	 */
	private void checkPwd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pwd = request.getParameter("pwd");
		pwd = MD5Encryption.createPassword(pwd);
		HttpSession sesion = request.getSession();
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		StuInfo stu = stuInfoBiz.findBySid(stuInfo.getSid());
		/*System.out.println(pwd+"1");
		System.out.println(stu.getPwd()+"2");*/
		if (pwd.equals(stu.getPwd())) {
			this.send(response, 1);
		} else {
			this.send(response, -1);
		}
	}

	/**
	 * 前台根据编号查找
	 * 
	 * @param request
	 * @param response
	 */
	private void findByFrontSid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		StuInfo stuInfo = (StuInfo) sesion.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stuInfo.getSid();
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		StuInfo stu = stuInfoBiz.findBySid(sid);
		this.send(response, stu);
	}

	/**
	 * 很具编号查找
	 * 
	 * @param request
	 * @param response
	 */
	private void findBySid(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String sid = request.getParameter("sid");
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.findBySid(sid));
	}

	/**
	 * 无照片添加学生
	 * 
	 * @param request
	 * @param response
	 */
	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();

		String cid = request.getParameter("cid");
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String sex = request.getParameter("sex");
		String cardID = request.getParameter("cardID");
		String tel = request.getParameter("tel");
		this.send(response, stuInfoBiz.addStuInfo(sid, sname, cid, sex, cardID, tel));
	}

	/**
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String mid = request.getParameter("mid");
		String cid = request.getParameter("cid");
		String grade = request.getParameter("grade");
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		Map<String, Object> map = stuInfoBiz.findByCondition(sid, sname, mid, cid, grade, pageNo, pageSize);
		this.send(response, map);

	}

	/**
	 * 修改学生密码
	 * 
	 * @param request
	 * @param response
	 */
	private void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String sid = request.getParameter("sid");
		String pwd = request.getParameter("pwd");
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.resetPwd(sid, pwd));
	}

	/**
	 * 学生图片批量上传
	 * 
	 * @param request
	 * @param response
	 */
	private void addStuInfoPhotos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FileUploadUtil uploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		int result = uploadUtil.uploadPhoto(pageContext);
		this.send(response, result);
	}

	/**
	 * 学生信息批量上传
	 * 
	 * @param request
	 * @param response
	 */
	private void addStuInfos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FileUploadUtil uploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		String filePath = uploadUtil.uploadExcelFile(pageContext);
		if (StringUtil.isNull(filePath)) {
			this.send(response, 0); // 插入失败
		} else {
			File fl = new File(pageContext.getServletContext().getRealPath("/") + filePath);
			if (fl.exists()) {
				ReadExcelToDB retdb = new ReadExcelToDB();
				IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
				List<List<String>> list = retdb.importExcel(fl);
				fl.deleteOnExit();
				int result = stuInfoBiz.addStuInfos(list);
				this.send(response, result);
			} else {
				this.send(response, 0); // 插入失败
			}
		}

	}

	/**
	 * 获取专业，班级信息
	 * 
	 * @param request
	 * @param response
	 */
	private void getInfos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.getInfo());
	}

	/**
	 * 分页查询
	 * 
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		@SuppressWarnings("unused")
		Map<String, Object> map = stuInfoBiz.findByPage(pageNo, pageSize);
		this.send(response, stuInfoBiz.findByPage(pageNo, pageSize));
	}

	/**
	 * 查找所有学生信息
	 * 
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		List<StuInfo> list = stuInfoBiz.finds();
		this.send(response, list);
	}

	/**
	 * 带图片修改学生
	 * 
	 * @param request
	 * @param response
	 */
	private void updateStuInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UploadUtil upLoadUtil = new UploadUtil();
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		Map<String, String> map = upLoadUtil.upload(pageContext);
		String sid = map.get("sid");
		String sname = map.get("sname");
		int cid = Integer.parseInt(map.get("cid"));
		String cardID = map.get("cardID");
		String pwd = MD5Encryption.createPassword(cardID.substring(12));
		String sex = map.get("sex");

		String photo = map.get("sid") + ".jpg";
		String tel = map.get("tel");

		StuInfo stuInfo = new StuInfo();
		stuInfo.setCardID(cardID);
		stuInfo.setSid(sid);
		stuInfo.setSname(sname);
		stuInfo.setTel(tel);
		stuInfo.setPhoto(photo);
		stuInfo.setPwd(pwd);
		stuInfo.setCid(cid);
		stuInfo.setSex(sex);
		int result = stuInfoBiz.updateStuInfo(stuInfo);
		this.send(response, result);
	}

	/**
	 * 带图片单个添加学生
	 * 
	 * @param request
	 * @param response
	 */
	private void addStuInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UploadUtil upLoadUtil = new UploadUtil();
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true,
				1024, true);
		Map<String, String> map = upLoadUtil.upload(pageContext);
		String sid = map.get("sid");
		String sname = map.get("sname");
		int cid = Integer.parseInt(map.get("cid"));
		String sex = map.get("sex");
		String cardID = map.get("cardID");
		String pwd = MD5Encryption.createPassword(cardID.substring(12));
		String photo = map.get("sid") + ".jpg";
		String tel = map.get("tel");

		StuInfo stuInfo = new StuInfo();
		stuInfo.setCardID(cardID);
		stuInfo.setSid(sid);
		stuInfo.setSname(sname);
		stuInfo.setTel(tel);
		stuInfo.setPhoto(photo);
		stuInfo.setPwd(pwd);
		stuInfo.setCid(cid);
		stuInfo.setSex(sex);
		int result = stuInfoBiz.addStuInfo(stuInfo);
		this.send(response, result);
	}

}
