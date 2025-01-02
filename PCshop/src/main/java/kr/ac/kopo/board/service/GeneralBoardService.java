package kr.ac.kopo.board.service;

import java.util.List;
import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;

public class GeneralBoardService extends BoardService {

	public GeneralBoardService(BoardDAO boardDao) {
		super(boardDao);
	}

	public List<BoardVO> selectAll() {
		return boardDao.selectAll();
	}

	public BoardVO selectByNo(int no) {
		return boardDao.selectByNo(no);
	}

	public boolean write(BoardVO board) {
		return boardDao.insert(board) > 0 ? true : false;
	}

	public void increaseViewCnt(int no) {
		boardDao.increaseViewCnt(no);
	}

	public boolean delete(int no) {
		return boardDao.delete(no);
	}

	public boolean updatePost(BoardVO board) {
		return boardDao.update(board);
	}
	
	public int getTotalBoardCount() {
		return boardDao.selectTotalcount();
	}
	
	public List<BoardVO> getBoardList(int offset, int pageSize) {
	    return boardDao.getBoardList(offset, pageSize);
	}
}
