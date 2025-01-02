package kr.ac.kopo.controller;

import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.component.service.ComponentService;

public class ProductDeleteController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 파라미터에서 ID 가져오기
            int id = Integer.parseInt(request.getParameter("id"));
            
            ComponentDAO componentDao = new ComponentDAOImpl();
            ComponentService componentService = new ComponentService(componentDao);
            boolean isDeleted = componentService.deleteComponent(id);

            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("success", isDeleted);

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(jsonResponse.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete component");
        }
        return null;
    }
}
