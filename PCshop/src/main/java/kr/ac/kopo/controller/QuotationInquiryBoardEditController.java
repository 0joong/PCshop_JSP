package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationInquiryService;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;

public class QuotationInquiryBoardEditController implements Controller {

    private final QuotationInquiryService quotationInquiryService;

    public QuotationInquiryBoardEditController() {
        this.quotationInquiryService = new QuotationInquiryService(new QuotationInquiryDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // HTTP 요청 메서드(GET/POST) 확인
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            // GET 요청: 수정 양식 페이지로 이동
            int postId = Integer.parseInt(request.getParameter("postId"));
            QuotationInquiryVO inquiry = quotationInquiryService.getInquiryById(postId);

            // 문의 데이터를 request에 설정하여 JSP로 전달
            request.setAttribute("inquiry", inquiry);
            return "/board/quotationInquiry/edit.jsp";
        } else if ("POST".equalsIgnoreCase(method)) {
            // POST 요청: 수정 사항 저장
            int postId = Integer.parseInt(request.getParameter("postId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            QuotationInquiryVO inquiry = new QuotationInquiryVO();
            inquiry.setPostId(postId);
            inquiry.setTitle(title);
            inquiry.setContent(content);

            quotationInquiryService.updateInquiry(inquiry);

            // 수정 후 상세보기 페이지로 리다이렉트
            return "redirect:/board/quotationInquiry/detail.do?postId=" + postId;
        }

        // 허용되지 않은 메서드 요청
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "허용되지 않은 요청입니다.");
        return null;
    }
}
