package kr.ac.kopo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.member.dao.MemberDAOImpl;
import kr.ac.kopo.member.service.MemberService;
import kr.ac.kopo.member.vo.MemberVO;

public class AdminUserManagementController implements Controller {

    private final MemberService memberService;

    public AdminUserManagementController() {
        this.memberService = new MemberService(new MemberDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchKeyword = request.getParameter("searchKeyword");

        List<MemberVO> userList;
        if (searchKeyword == null || searchKeyword.isEmpty()) {
            userList = memberService.getAllUsers();
        } else {
            userList = memberService.searchUsers(searchKeyword);
        }

        request.setAttribute("userList", userList);
        for(MemberVO member : userList) {
        	System.out.println(member);
        }
        return "/admin/userManagement.jsp";
    }
}
