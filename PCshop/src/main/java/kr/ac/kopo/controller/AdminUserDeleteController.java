package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;

public class AdminUserDeleteController implements Controller {

    private final MemberService memberService;

    public AdminUserDeleteController() {
        this.memberService = new MemberService(new MemberDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");

        if (userId == null || userId.isEmpty()) {
            request.setAttribute("errMsg", "삭제할 사용자 ID가 유효하지 않습니다.");
            return "/admin/userManagement.jsp";
        }

        boolean isDeleted = memberService.deleteUserById(userId);

        if (!isDeleted) {
            request.setAttribute("resultMsg", "사용자 삭제에 실패했습니다.");
        } else {
            request.setAttribute("resultMsg", "사용자가 성공적으로 삭제되었습니다.");
        }

        return "/admin/userManagement.do";
    }
}
