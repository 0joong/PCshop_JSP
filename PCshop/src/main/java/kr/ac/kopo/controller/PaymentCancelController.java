package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PaymentCancelController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 사용자가 결제를 취소했을 때의 처리 로직
        // 예: 로그 기록, 사용자 메시지 설정 등
        System.out.println("결제 취소 요청 처리");

        // 메시지를 request 영역에 설정
        request.setAttribute("cancelMessage", "결제가 취소되었습니다. 다시 시도하거나 다른 결제 수단을 이용해주세요.");

        // 결제 취소 JSP 페이지로 이동
        return "/order/cancel.jsp";
    }
}
