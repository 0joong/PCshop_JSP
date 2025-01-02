package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.dao.BoardDAOImpl;
import kr.ac.kopo.board.service.BoardService;
import kr.ac.kopo.board.service.GeneralBoardService;
import kr.ac.kopo.board.vo.BoardVO;

public class GeneralBoardDetailController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			int no = Integer.parseInt(request.getParameter("postId"));
			System.out.println("no : " + no);
			BoardDAO boardDao = new BoardDAOImpl();
			BoardService boardService = new GeneralBoardService(boardDao);
			boardService.increaseViewCnt(no);
			BoardVO board = boardService.selectByNo(no);
			request.setAttribute("board", board);
			
			return "/board/general/detail.jsp";			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "게시글 상세 정보를 불러오는 중 오류가 발생했습니다.");
			return "/index.jsp";
		}
	}
	
	
}
