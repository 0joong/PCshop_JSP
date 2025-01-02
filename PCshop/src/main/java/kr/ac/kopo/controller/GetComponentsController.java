package kr.ac.kopo.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.component.service.ComponentService;
import kr.ac.kopo.product.vo.ComponentVO;

public class GetComponentsController implements Controller {
	private static final Gson gson = new Gson();
	private static final Logger logger = Logger.getLogger(GetComponentsController.class.getName());
	private final ComponentService componentService;


	public GetComponentsController() {
	    this.componentService = new ComponentService(new ComponentDAOImpl());
	}


	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			String type = request.getParameter("type"); // "cpu", "mainboard" 등
			logger.info("Request received for type: " + type);
			System.out.println("requested type : " + type);

			if (type == null || type.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Type parameter is missing");
				return null;
			}

			if (!"cpu".equalsIgnoreCase(type) && !"mainboard".equalsIgnoreCase(type)) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid type parameter");
				return null;
			}

			// Service를 통해 데이터 가져오기
			ComponentDAO componentDao = new ComponentDAOImpl();
			ComponentService componentService = new ComponentService(componentDao);
			List<ComponentVO> components = componentService.getComponentsByCategory(type);

			// JSON 변환 및 반환
			String jsonResponse = gson.toJson(components);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonResponse);
			out.flush();
		} catch (Exception e) {
			logger.severe("Error processing request: " + e.getMessage());
			e.printStackTrace();
			try {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"An error occurred while processing your request.");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
