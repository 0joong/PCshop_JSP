package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.order.dao.OrderDAOImpl;
import kr.ac.kopo.order.service.OrderService;
import kr.ac.kopo.order.vo.OrderVO;

import java.util.List;

public class MyPageController implements Controller {

    private MemberService memberService;
    private OrderService orderService;
    
    public MyPageController() {
    	memberService = new MemberService(new MemberDAOImpl());
    	orderService = new OrderService(new OrderDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("user");

        if (user == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/loginForm.jsp");
            return null;
        }

        // 사용자 정보
        MemberVO memberInfo = memberService.getMemberInfo(user.getId());
        request.setAttribute("member", memberInfo);

        // 주문 목록
        List<OrderVO> orderList = orderService.getOrdersByUserId(user.getId());
        request.setAttribute("orderList", orderList);

        // JSP로 포워드
        return "/member/myPage.jsp";
    }

}
