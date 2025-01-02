package kr.ac.kopo.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationInquiryService;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;

public class QuotationInquiryBoardListController implements Controller {

    private final QuotationInquiryService quotationInquiryService;

    public QuotationInquiryBoardListController() {
        this.quotationInquiryService = new QuotationInquiryService(new QuotationInquiryDAOImpl());
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 문의 목록 가져오기
            List<QuotationInquiryVO> inquiryList = quotationInquiryService.getAllInquiries();

            // JSP에 전달할 데이터 설정
            request.setAttribute("inquiryList", inquiryList);

            // 목록 JSP 페이지로 이동
            return "/board/quotationInquiry/list.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errMsg", "문의 목록을 가져오는 중 오류가 발생했습니다.");
            return "/index.jsp";
        }
    }
}
