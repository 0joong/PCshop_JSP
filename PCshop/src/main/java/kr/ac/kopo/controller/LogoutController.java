package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션 무효화
        if (request.getSession(false) != null) { // 세션이 존재할 경우만 처리
            request.getSession().invalidate();
        }

        // 리다이렉트 후 null 반환
        return "redirect:/index.jsp"; // sendRedirect가 호출되므로 JSP 경로 반환 불필요
    }
}
