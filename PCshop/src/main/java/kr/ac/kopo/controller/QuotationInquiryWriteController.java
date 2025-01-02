package kr.ac.kopo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAOImpl;
import kr.ac.kopo.board.quotation.service.QuotationInquiryService;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;
import kr.ac.kopo.member.vo.MemberVO;

public class QuotationInquiryWriteController implements Controller {

    private final QuotationInquiryService quotationInquiryService 
    = new QuotationInquiryService(new QuotationInquiryDAOImpl());

    public QuotationInquiryWriteController() {}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
    	// 세션에 사용자 정보가 없을 경우
        if (user == null) {
            session.setAttribute("errMsg", "잘못된 접근입니다.");
            return "redirect:/index.jsp";
        }
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // 글쓰기 폼으로 연결
            return "/board/quotationInquiry/inquiryWrite.jsp";
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            // 데이터 처리 및 저장
        	String userId = ((MemberVO) user).getId(); // 세션에서 사용자 ID 가져오기
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            QuotationInquiryVO inquiry = new QuotationInquiryVO();
            inquiry.setUserId(userId);
            inquiry.setTitle(title);
            inquiry.setContent(content);

            quotationInquiryService.createInquiry(inquiry);

            return "redirect:/board/quotationInquiry/list.do";
        }
        return null;
    }
}
