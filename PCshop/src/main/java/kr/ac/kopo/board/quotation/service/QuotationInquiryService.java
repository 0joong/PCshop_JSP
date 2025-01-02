package kr.ac.kopo.board.quotation.service;

import java.util.List;

import kr.ac.kopo.board.quotation.dao.QuotationInquiryDAO;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;

public class QuotationInquiryService {
	
	private final QuotationInquiryDAO quotationInquiryDAO;

    public QuotationInquiryService(QuotationInquiryDAO QuotationInquiryDao) {
        this.quotationInquiryDAO = QuotationInquiryDao;
    }

    public List<QuotationInquiryVO> getAllInquiries() {
        return quotationInquiryDAO.getAllInquiries();
    }

    public QuotationInquiryVO getInquiryById(int postId) {
        return quotationInquiryDAO.getInquiryById(postId);
    }

    public boolean createInquiry(QuotationInquiryVO inquiry) {
        return quotationInquiryDAO.createInquiry(inquiry) > 0;
    }

    public boolean updateInquiry(QuotationInquiryVO inquiry) {
        return quotationInquiryDAO.updateInquiry(inquiry) > 0;
    }

    public boolean deleteInquiry(int postId) {
        return quotationInquiryDAO.deleteInquiry(postId) > 0;
    }
}
