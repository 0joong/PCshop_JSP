package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationAnswerDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationAnswerService;
import kr.ac.kopo.board.quotation.vo.QuotationAnswerVO;

public class QuotationAnswerWriteController implements Controller {

    private final QuotationAnswerService quotationAnswerService
    = new QuotationAnswerService(new QuotationAnswerDAOImpl());

    public QuotationAnswerWriteController() {
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // 답변 작성 폼으로 연결
            request.setAttribute("inquiryId", request.getParameter("inquiryId"));
            return "/board/quotationAnswer/write.jsp";
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            // 데이터 처리 및 저장
            int inquiryId = Integer.parseInt(request.getParameter("inquiryId"));
            String userId = request.getParameter("userId");
            String content = request.getParameter("content");

            QuotationAnswerVO answer = new QuotationAnswerVO();
            answer.setInquiryId(inquiryId);
            answer.setUserId(userId);
            answer.setContent(content);

            quotationAnswerService.createAnswer(answer);

            return "redirect:/board/quotationInquiry/detail.do?postId=" + inquiryId;
        }
        return null;
    }
}
