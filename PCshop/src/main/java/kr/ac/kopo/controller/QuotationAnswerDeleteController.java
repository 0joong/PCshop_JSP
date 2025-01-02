package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationAnswerDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationAnswerService;

public class QuotationAnswerDeleteController implements Controller {

    private QuotationAnswerService quotationAnswerService;

    public QuotationAnswerDeleteController() {}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	quotationAnswerService = new QuotationAnswerService(new QuotationAnswerDAOImpl());
        // 답변 ID 및 문의 ID 가져오기
        int answerId = Integer.parseInt(request.getParameter("answerId"));
        int inquiryId = Integer.parseInt(request.getParameter("inquiryId"));

        // 답변 삭제 처리
        quotationAnswerService.deleteAnswer(answerId);

        // 삭제 후 해당 문의의 상세보기 페이지로 리다이렉트
        return "redirect:/board/quotationInquiry/detail.do?postId=" + inquiryId;
    }
}
