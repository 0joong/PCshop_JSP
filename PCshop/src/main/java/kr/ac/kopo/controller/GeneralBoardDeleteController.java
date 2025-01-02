package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.dao.*;
import kr.ac.kopo.board.service.GeneralBoardService;
import kr.ac.kopo.member.vo.MemberVO;

public class GeneralBoardDeleteController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		
		if (user == null) {
            request.setAttribute("errMsg", "로그인이 필요합니다.");
            return "/loginForm.jsp";
        }
		
		BoardDAO boardDao = new BoardDAOImpl();
		GeneralBoardService generalBoardSerivce = new GeneralBoardService(boardDao);
		generalBoardSerivce.delete(postId);
		
		return "/board/general/list.do";
	}

}
