package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationInquiryService;

public class QuotationInquiryBoardDeleteController implements Controller {

    private final QuotationInquiryService quoationInquiryService;

    public QuotationInquiryBoardDeleteController() {
        this.quoationInquiryService = new QuotationInquiryService(new QuotationInquiryDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int postId = Integer.parseInt(request.getParameter("postId"));

        quoationInquiryService.deleteInquiry(postId);

        return "redirect:/board/quotationInquiry/list.do";
    }
}