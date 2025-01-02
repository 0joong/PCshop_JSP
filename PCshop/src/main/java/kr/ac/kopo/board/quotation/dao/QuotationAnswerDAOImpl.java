package kr.ac.kopo.board.quotation.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import kr.ac.kopo.board.quotation.vo.QuotationAnswerVO;
import kr.ac.kopo.mybatis.MyConfig;

public class QuotationAnswerDAOImpl implements QuotationAnswerDAO {

    private SqlSession sqlSession;

    public QuotationAnswerDAOImpl() {
        this.sqlSession = new MyConfig().getInstance();
    }

    @Override
    public int insertAnswer(QuotationAnswerVO answer) {
        sqlSession.clearCache();
        int result = sqlSession.insert("kr.ac.kopo.board.quotation.dao.QuotationAnswerMapper.insertAnswer", answer);
        sqlSession.commit();
        return result;
    }

    @Override
    public List<QuotationAnswerVO> selectAnswersByInquiryId(int inquiryId) {
        sqlSession.clearCache();
        return sqlSession.selectList("kr.ac.kopo.board.quotation.dao.QuotationAnswerMapper.selectAnswersByInquiryId", inquiryId);
    }

    @Override
    public QuotationAnswerVO selectAnswerById(int answerId) {
        sqlSession.clearCache();
        return sqlSession.selectOne("kr.ac.kopo.board.quotation.dao.QuotationAnswerMapper.selectAnswerById", answerId);
    }

    @Override
    public int deleteAnswer(int answerId) {
        sqlSession.clearCache();
        int result = sqlSession.delete("kr.ac.kopo.board.quotation.dao.QuotationAnswerMapper.deleteAnswer", answerId);
        sqlSession.commit();
        return result;
    }
}
