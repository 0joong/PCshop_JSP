package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.LoginVO;
import kr.ac.kopo.member.vo.MemberVO;

public class LoginController implements Controller {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        System.out.println(id + " + " + password);
        MemberDAO memberDao = new MemberDAOImpl();
        MemberService memberService = new MemberService(memberDao);

        LoginVO login = new LoginVO(id, password);
        MemberVO member = memberService.login(login);

        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", member);
            return "redirect:/index.jsp";
        } else {
            request.setAttribute("errMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "/member/loginForm.jsp";
        }
    }
}