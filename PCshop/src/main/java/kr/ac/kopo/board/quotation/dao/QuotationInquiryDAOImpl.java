package kr.ac.kopo.board.quotation.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import kr.ac.kopo.board.quotation.vo.QuotationInquiryVO;
import kr.ac.kopo.mybatis.MyConfig;

public class QuotationInquiryDAOImpl implements QuotationInquiryDAO {

    private SqlSession sqlSession;

    public QuotationInquiryDAOImpl() {
        this.sqlSession = new MyConfig().getInstance();
    }

    @Override
    public List<QuotationInquiryVO> getAllInquiries() {
        sqlSession.clearCache();
        return sqlSession.selectList("kr.ac.kopo.quotation.dao.QuotationInquiryMapper.getAllInquiries");
    }

    @Override
    public QuotationInquiryVO getInquiryById(int postId) {
        sqlSession.clearCache();
        return sqlSession.selectOne("kr.ac.kopo.quotation.dao.QuotationInquiryMapper.getInquiryById", postId);
    }

    @Override
    public int createInquiry(QuotationInquiryVO inquiry) {
        sqlSession.clearCache();
        int result = sqlSession.insert("kr.ac.kopo.quotation.dao.QuotationInquiryMapper.createInquiry", inquiry);
        return result;
    }

    @Override
    public int updateInquiry(QuotationInquiryVO inquiry) {
        sqlSession.clearCache();
        int result = sqlSession.update("kr.ac.kopo.quotation.dao.QuotationInquiryMapper.updateInquiry", inquiry);
        return result;
    }

    @Override
    public int deleteInquiry(int postId) {
        sqlSession.clearCache();
        int result = sqlSession.delete("kr.ac.kopo.quotation.dao.QuotationInquiryMapper.deleteInquiry", postId);
        return result;
    }
}
