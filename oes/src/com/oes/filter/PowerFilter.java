package com.oes.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oes.util.SessionAttributeKey;

/**
 * Servlet Filter implementation class PowerFilter
 */
@WebFilter("/PowerFilter")
public class PowerFilter implements Filter {

	public PowerFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		HttpServletResponse resp = (HttpServletResponse) response;
		Object obj = session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		if (obj == null) {
			String path = request.getScheme() + "://" + req.getServerName();
			resp.setContentType("text/html;charset-utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('请先登录...');location.href='" + path + "'</script>");
			out.flush();
			out.close();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
