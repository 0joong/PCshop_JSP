package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.List;

import kr.ac.kopo.component.service.ComponentService;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.product.vo.ComponentVO;

public class GetProductController implements Controller {
	
	
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idParam = request.getParameter("id");
        
    	
        // Validate the parameter
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
            return null;
        }

        try {
            int itemCd = Integer.parseInt(idParam);
            
            ComponentDAO componentDao = new ComponentDAOImpl();
        	ComponentService componentService = new ComponentService(componentDao);

            // Fetch product by ID
            ComponentVO product = componentService.getComponentById(itemCd);

            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return null;
            }

            // Return the product as JSON
            String jsonResponse = new Gson().toJson(product);
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(jsonResponse);
            out.flush();
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
            e.printStackTrace();
        }
        return null;
    }
}
