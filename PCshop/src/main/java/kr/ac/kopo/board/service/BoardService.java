package kr.ac.kopo.board.service;

import java.util.List;

import kr.ac.kopo.board.dao.BoardDAO;
import kr.ac.kopo.board.vo.BoardVO;

public class BoardService {
	
	BoardDAO boardDao;
	
	public BoardService(BoardDAO boardDao) {
		this.boardDao = boardDao;
	}
	
	public List<BoardVO> selectAll() {
		List<BoardVO> boardList = boardDao.selectAll();
		return boardList;
	}
	
	public BoardVO selectByNo(int no) {
		BoardVO board = boardDao.selectByNo(no);
		return board;
	}
	
	public int insert(BoardVO board) {
		return boardDao.insert(board);
	}
	
	public void increaseViewCnt(int no) {
		
	}
	
	
}
