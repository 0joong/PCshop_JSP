package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PaymentFailController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 결제 실패 처리 로직
        System.out.println("결제 실패 요청 처리");

        // 실패 메시지를 설정
        request.setAttribute("failMessage", "결제가 실패했습니다. 다시 시도해주세요.");

        // 실패 페이지로 포워드
        return "/order/paymentFail.jsp";
    }
}
