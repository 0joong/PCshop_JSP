package kr.ac.kopo.board.quotation.dao;

import java.util.List;
import kr.ac.kopo.board.quotation.vo.QuotationAnswerVO;

public interface QuotationAnswerDAO {

    // 답변 생성
    int insertAnswer(QuotationAnswerVO answer);

    // 특정 질문의 모든 답변 조회
    List<QuotationAnswerVO> selectAnswersByInquiryId(int inquiryId);

    // 특정 답변 조회
    QuotationAnswerVO selectAnswerById(int answerId);

    // 답변 삭제
    int deleteAnswer(int answerId);
}
