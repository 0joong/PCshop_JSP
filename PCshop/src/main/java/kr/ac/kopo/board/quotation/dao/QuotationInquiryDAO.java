package kr.ac.kopo.board.quotation.dao;

import java.util.List;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;

public interface QuotationInquiryDAO {
	
	List<QuotationInquiryVO> getAllInquiries(); // 모든 문의 조회
    QuotationInquiryVO getInquiryById(int postId); // 특정 문의 조회
    int createInquiry(QuotationInquiryVO inquiry); // 문의 생성
    int updateInquiry(QuotationInquiryVO inquiry); // 문의 수정
    int deleteInquiry(int postId); // 문의 삭제
}
