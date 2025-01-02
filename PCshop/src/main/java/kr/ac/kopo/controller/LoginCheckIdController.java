package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;

import java.io.PrintWriter;

public class LoginCheckIdController implements Controller {

    private MemberService memberService;

    public LoginCheckIdController() {
        this.memberService = new MemberService(new MemberDAOImpl()); // 기본 생성자를 사용하여 서비스 초기화
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 클라이언트에서 요청한 아이디를 가져옵니다.
        String userId = request.getParameter("id");

        // 아이디 중복 체크
        boolean isAvailable = memberService.isIdAvailable(userId);

        // 응답 형식을 JSON으로 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 응답 결과를 작성
        PrintWriter out = response.getWriter();
        if (isAvailable) {
            out.print("{\"result\":\"available\"}");
        } else {
            out.print("{\"result\":\"unavailable\"}");
        }
        out.flush();

        // 이 컨트롤러는 페이지 이동이 필요 없으므로 null 반환
        return null;
    }
}
