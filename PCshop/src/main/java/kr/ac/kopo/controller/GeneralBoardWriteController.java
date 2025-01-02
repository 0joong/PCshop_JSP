package kr.ac.kopo.controller;

import java.io.InputStream;
import java.util.UUID;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.dao.BoardDAOImpl;
import kr.ac.kopo.board.service.BoardService;
import kr.ac.kopo.board.service.GeneralBoardService;
import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.UploadToS3;
import kr.ac.kopo.util.FileNamePolicy;

public class GeneralBoardWriteController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
        	
        	Part part = request.getPart("file");
        	System.out.println(request.getParameter("title"));
       
        	
            // POST 요청 데이터 가져오기
        	Object memberObj = request.getSession().getAttribute("user");
            if (memberObj == null) {
                request.setAttribute("errMSg", "로그인이 필요한 서비스입니다.");
                return "/index.jsp";
            }
            String writer = ((MemberVO) memberObj).getId();
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Part filePart = request.getPart("file"); // 파일 업로드 처리
            String fileUrl = null;

            // 파일 업로드 처리
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();

                // S3에 업로드 후 URL을 생성
                fileUrl = new UploadToS3().uploadToS3(fileName, fileContent);
            }

            // VO 생성
            BoardVO board = new BoardVO();
            board.setTitle(title);
            board.setContent(content);
            board.setUserId(writer);
            board.setFileUrl(fileUrl);
            System.out.println(board);
            // DAO를 통한 데이터베이스 저장
            BoardDAO boardDao = new BoardDAOImpl();
            BoardService boardService = new GeneralBoardService(boardDao);
            boardService.insert(board);
            

            // 게시글 목록 페이지로 리다이렉트
            return "redirect:/board/general/list.do";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMsg", "게시글 작성 중 오류가 발생했습니다.");
            return "/index.jsp";
        }
    }
}