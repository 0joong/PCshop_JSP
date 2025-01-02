package kr.ac.kopo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.dao.BoardDAOImpl;
import kr.ac.kopo.board.service.BoardService;
import kr.ac.kopo.board.service.GeneralBoardService;
import kr.ac.kopo.board.vo.BoardVO;

public class GeneralBoardListController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // DAO 및 Service 초기화
        BoardDAO boardDao = new BoardDAOImpl();
        GeneralBoardService boardService = new GeneralBoardService(boardDao);

        // 파라미터로 받은 현재 페이지 (기본값: 1)
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        // 페이지당 게시글 수
        int pageSize = 10;

        // 전체 게시글 개수
        int totalCount = boardService.getTotalBoardCount();

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // 현재 페이지에 해당하는 게시글 가져오기
        int offset = (currentPage - 1) * pageSize;
        List<BoardVO> boardList = boardService.getBoardList(offset, pageSize);
        
        System.out.println("currentPage: " + currentPage);
        System.out.println("offset: " + offset);
        System.out.println("pageSize: " + pageSize);


        // JSP에 데이터 전달
        request.setAttribute("generalBoardList", boardList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        return "/board/general/list.jsp";
    }
}
