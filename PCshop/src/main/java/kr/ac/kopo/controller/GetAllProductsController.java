package kr.ac.kopo.controller;

import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.component.service.ComponentService;
import kr.ac.kopo.product.vo.ComponentVO;

public class GetAllProductsController implements Controller {

  
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 모든 부품 데이터 가져오기
        	ComponentDAO componentDao = new ComponentDAOImpl();
        	ComponentService componentService = new ComponentService(componentDao);
            List<ComponentVO> components = componentService.getAllComponents();

            // JSON 변환 및 응답 설정
            String jsonResponse = new Gson().toJson(components);
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonResponse);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load products");
        }
        return null; // JSP로 이동하지 않고 JSON 데이터만 반환
    }
}
