package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

public class RegisterController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");

		MemberDAO memberDao = new MemberDAOImpl();
		MemberService memberService = new MemberService(memberDao);

		MemberVO member = new MemberVO();
		member.setId(id);
		member.setEmail(email);
		member.setPassword(password);
		member.setName(name);
		member.setPhone(phone);

		if(memberService.register(member)) {
			session.setAttribute("user", member);
			request.setAttribute("alertMsg", "회원가입 성공");
		} else {
			request.setAttribute("alertMsg", "회원가입 실패");
		}
		return "redirect:/index.jsp";
	}
}
