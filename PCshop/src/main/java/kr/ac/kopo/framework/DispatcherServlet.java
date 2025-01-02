package kr.ac.kopo.framework;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.controller.*;

@MultipartConfig(
		maxFileSize = 1024*1024*10,
		maxRequestSize = 1024*1024*10,
		fileSizeThreshold = 1024*1024*15
)
public class DispatcherServlet extends HttpServlet {
	
	private HandlerMapping mappings;
	@Override
	public void init(ServletConfig config) throws ServletException {
		String propName = config.getInitParameter("propName");
		System.out.println("propName : " + propName);
		mappings = new HandlerMapping(propName);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		uri = uri.substring(contextPath.length());
		System.out.println("요청 uri : " + uri);
		System.out.println("contextPath : " + contextPath);
		
		
		try {
			Controller control = mappings.getController(uri);			
			String callPage = control.handleRequest(request, response);
			
			if (callPage == null) {
			    return; // JSON 응답과 같은 경우, 더 이상 처리하지 않음
			}
			if(callPage.startsWith("redirect:")) {
				callPage = callPage.substring("redirect:".length());
				response.sendRedirect(request.getContextPath() + callPage);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
				dispatcher.forward(request, response);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("컨트롤러를 찾을 수 없습니다.");
		}
	}
}
