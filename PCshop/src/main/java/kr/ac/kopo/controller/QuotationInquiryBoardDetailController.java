package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAOImpl;
import kr.ac.kopo.board.quotation.dao.QuotationAnswerDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationInquiryService;
import kr.ac.kopo.board.quotation.service.QuotationAnswerService;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;
import kr.ac.kopo.board.quotation.vo.QuotationAnswerVO;

import java.util.List;

public class QuotationInquiryBoardDetailController implements Controller {

    private final QuotationInquiryService quotationInquiryService;
    private final QuotationAnswerService quotationAnswerService;

    public QuotationInquiryBoardDetailController() {
        this.quotationInquiryService = new QuotationInquiryService(new QuotationInquiryDAOImpl());
        this.quotationAnswerService = new QuotationAnswerService(new QuotationAnswerDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int postId = Integer.parseInt(request.getParameter("postId"));

        // 문의 상세 정보 조회
        QuotationInquiryVO inquiry = quotationInquiryService.getInquiryById(postId);
        request.setAttribute("inquiry", inquiry);

        // 해당 문의의 답변 목록 조회
        List<QuotationAnswerVO> answerList = quotationAnswerService.getAnswersByInquiryId(postId);
        request.setAttribute("answerList", answerList);

        return "/board/quotationInquiry/detail.jsp";
    }
}
