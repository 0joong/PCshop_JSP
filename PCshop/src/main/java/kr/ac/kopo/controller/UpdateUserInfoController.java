package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

public class UpdateUserInfoController implements Controller {

    private final MemberService memberService;

    public UpdateUserInfoController() {
        this.memberService = new MemberService(new MemberDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("username");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        HttpSession session = request.getSession();

        // Validate current password
        if (!memberService.validatePassword(userId, currentPassword)) {
            session.setAttribute("errMsg", "현재 비밀번호가 올바르지 않습니다.");
            return "redirect:/member/myPage.do";
        }

        // Check if new password matches confirm password
        if (newPassword != null && !newPassword.equals(confirmPassword)) {
            session.setAttribute("errMsg", "새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "redirect:/member/myPage.do";
        }

        // Update user info
        MemberVO user = new MemberVO();
        user.setId(userId);
        user.setEmail(email);
        user.setPhone(phone);

        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        boolean isUpdated = memberService.updateUserInfo(user);

        if (!isUpdated) {
            session.setAttribute("resultMsg", "정보 수정에 실패했습니다.");
            return "redirect:/member/myPage.do";
        }

        session.setAttribute("resultMsg", "정보가 성공적으로 수정되었습니다.");
        return "redirect:/member/myPage.do";
    }
}
