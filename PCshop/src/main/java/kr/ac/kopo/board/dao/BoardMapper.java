package kr.ac.kopo.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.kopo.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	List<BoardVO> selectAll();
	
	BoardVO selectByNo(int no);
	
	int insert(BoardVO board);

	int update(BoardVO board);

	int delete(int no);
	
	void increaseViewCnt(int no);

}
