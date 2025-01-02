package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationInquiryService;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;

public class QuotationInquiryBoardWriteController implements Controller {

    private final QuotationInquiryService quotationInquiryService;

    public QuotationInquiryBoardWriteController() {
        this.quotationInquiryService = new QuotationInquiryService(new QuotationInquiryDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // HTTP Method 확인
        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            // 글쓰기 폼으로 연결
            return "/board/quoationInquiry/write.jsp";
        } else if ("POST".equalsIgnoreCase(method)) {
            // 폼 데이터 처리 및 등록
            String userId = request.getParameter("userId");
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            QuotationInquiryVO inquiry = new QuotationInquiryVO();
            inquiry.setUserId(userId);
            inquiry.setTitle(title);
            inquiry.setContent(content);

            quotationInquiryService.createInquiry(inquiry);

            // 등록 후 목록 페이지로 리다이렉트
            return "redirect:/board/quoationInquiry/list.do";
        } else {
            // GET, POST 외의 요청은 에러 페이지로 연결
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "허용되지 않은 요청입니다.");
            return null;
        }
    }
}
