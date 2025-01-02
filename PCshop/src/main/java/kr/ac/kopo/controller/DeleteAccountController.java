package kr.ac.kopo.controller;

import java.net.URLEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

public class DeleteAccountController implements Controller {

    private MemberService memberService;

    public DeleteAccountController() {
        this.memberService = new MemberService(new MemberDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        MemberVO user = (MemberVO) session.getAttribute("user");

        if (user == null) {
            // 로그인하지 않은 상태라면 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/loginForm.jsp");
            return null;
        }

        String inputPassword = request.getParameter("password");

        if (inputPassword == null || inputPassword.isEmpty()) {
            request.setAttribute("errMsg", "비밀번호를 입력해주세요.");
            return "/member/deleteAccountForm.jsp"; // 회원탈퇴 폼으로 포워드
        }

        boolean isPasswordValid = memberService.validatePassword(user.getId(), inputPassword);

        if (!isPasswordValid) {
            request.setAttribute("errMsg", "비밀번호가 일치하지 않습니다.");
            request.setAttribute("activeTap", "deleteAccount");
            return "/member/myPage.do"; 
        }

        boolean isDeleted = memberService.deleteAccount(user.getId());

        if (isDeleted) {
            // 탈퇴 성공: 세션 종료 후 메인 페이지로 리다이렉트
        	session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp?resultMsg=" + URLEncoder.encode("회원 탈퇴에 성공했습니다.", "UTF-8"));
            return null;
        } else {
            request.setAttribute("resultMsg", "회원 탈퇴에 실패했습니다. 다시 시도해주세요.");
            return "/index.jsp";
        }
    }
}
