package kr.ac.kopo.board.quotation.service;

import java.util.List;
import kr.ac.kopo.board.quotation.dao.QuotationAnswerDAO;
import kr.ac.kopo.board.quotation.vo.QuotationAnswerVO;

public class QuotationAnswerService {

    private final QuotationAnswerDAO quotationAnswerDAO;

    public QuotationAnswerService(QuotationAnswerDAO quotationAnswerDAO) {
        this.quotationAnswerDAO = quotationAnswerDAO;
    }

    // 답변 생성
    public boolean createAnswer(QuotationAnswerVO answer) {
        return quotationAnswerDAO.insertAnswer(answer) > 0;
    }

    // 특정 질문에 대한 모든 답변 조회
    public List<QuotationAnswerVO> getAnswersByInquiryId(int inquiryId) {
        return quotationAnswerDAO.selectAnswersByInquiryId(inquiryId);
    }

    // 특정 답변 조회
    public QuotationAnswerVO getAnswerById(int answerId) {
        return quotationAnswerDAO.selectAnswerById(answerId);
    }

    // 답변 삭제
    public boolean deleteAnswer(int answerId) {
        return quotationAnswerDAO.deleteAnswer(answerId) > 0;
    }
}
