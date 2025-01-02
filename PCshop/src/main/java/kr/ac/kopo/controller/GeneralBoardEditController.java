package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.dao.BoardDAOImpl;
import kr.ac.kopo.board.service.GeneralBoardService;
import kr.ac.kopo.board.vo.BoardVO;

public class GeneralBoardEditController implements Controller {

    private GeneralBoardService boardService;

    public GeneralBoardEditController() {
        this.boardService = new GeneralBoardService(new BoardDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            // 게시글 수정 화면 요청
            int postId = Integer.parseInt(request.getParameter("postId"));
            BoardVO board = boardService.selectByNo(postId);

            if (board == null) {
                request.setAttribute("errMsg", "해당 게시글이 존재하지 않습니다.");
                return "/board/list.do"; // 게시글 목록으로 리다이렉트
            }

            request.setAttribute("board", board);
            return "/board/general/edit.jsp";

        } else if ("POST".equalsIgnoreCase(method)) {
            // 게시글 수정 처리 요청
            int postId = Integer.parseInt(request.getParameter("postId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            BoardVO updatedBoard = new BoardVO();
            updatedBoard.setPostId(postId);
            updatedBoard.setTitle(title);
            updatedBoard.setContent(content);

            boolean isUpdated = boardService.updatePost(updatedBoard);

            if (isUpdated) {
                return "redirect:/board/general/detail.do?postId=" + postId;
            } else {
                request.setAttribute("errMsg", "게시글 수정에 실패했습니다.");
                return "redirect:/board/general/detail.do?postId=" + postId;
            }
        }

        return "/board/general/list.do"; // 기본적으로 목록으로 리다이렉트
    }
}
