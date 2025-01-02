package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.component.dao.ComponentDAO;
import kr.ac.kopo.component.dao.ComponentDAOImpl;
import kr.ac.kopo.component.service.ComponentService;

public class CompatibilityCheckController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			 // CPU와 메인보드 ID 가져오기
            int cpuId = Integer.parseInt(request.getParameter("cpuId"));
            System.out.println("cpuId : " + cpuId);
            int mainboardId = Integer.parseInt(request.getParameter("mainboardId"));
            System.out.println("mainboardId" + mainboardId);

            // DAO를 통해 소켓 정보 가져오기
            ComponentDAO componentDao = new ComponentDAOImpl();
            ComponentService componentService = new ComponentService(componentDao);
            String cpuSocket = componentService.getSocketByComponentId(cpuId, "cpu");
            String mainboardSocket = componentService.getSocketByComponentId(mainboardId, "mainboard");
            System.out.println("cpu socket : " + cpuSocket);
            System.out.println("mainboard socket : " + mainboardSocket);

            // 호환성 검사
            boolean isCompatible = cpuSocket.equals(mainboardSocket);

            // JSON 응답 반환
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"compatible\": " + isCompatible + "}");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "호환성 검사 중 오류가 발생했습니다.");
		}
		
		return null;
	}

}
