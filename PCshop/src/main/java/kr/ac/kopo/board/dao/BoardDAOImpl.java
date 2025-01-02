package kr.ac.kopo.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.ac.kopo.board.vo.BoardVO;
import kr.ac.kopo.mybatis.MyConfig;

public class BoardDAOImpl implements BoardDAO{
	
	private BoardMapper boardMapper;
	private SqlSession sqlSession;
			
	public BoardDAOImpl() {
		sqlSession = new MyConfig().getInstance();
		boardMapper = sqlSession.getMapper(BoardMapper.class);
	}
	
	 

	@Override
	public List<BoardVO> selectAll() {
		return boardMapper.selectAll();
	}

	@Override
	public BoardVO selectByNo(int no) {
		//return boardMapper.selectByNo(no);
		return sqlSession.selectOne("kr.ac.kopo.board.dao.BoardMapper.selectByNo", no);
	}

	@Override
	public int insert(BoardVO board) {
		return boardMapper.insert(board);
	}

	@Override
	public boolean update(BoardVO board) {
		return boardMapper.update(board) > 0 ? true : false;
	}

	@Override
	public boolean delete(int no) {	
		return boardMapper.delete(no) > 0 ? true : false;
	}

	@Override
	public void increaseViewCnt(int no) {
		boardMapper.increaseViewCnt(no);
	}

	@Override
	public List<BoardVO> selectByWriter(String writer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public int selectTotalcount() {
        // MyBatis 매퍼의 selectTotalCount 쿼리 실행
        return sqlSession.selectOne("kr.ac.kopo.board.dao.BoardMapper.selectTotalCount");
    }

    @Override
    public List<BoardVO> getBoardList(int offset, int pageSize) {
        // MyBatis 매퍼에 필요한 파라미터를 Map으로 전달
        Map<String, Integer> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);

        // MyBatis 매퍼의 selectBoardList 쿼리 실행
        return sqlSession.selectList("kr.ac.kopo.board.dao.BoardMapper.selectBoardList", params);
    }
}
