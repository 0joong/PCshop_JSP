package kr.ac.kopo.board.dao;

import java.util.List;
import kr.ac.kopo.board.vo.BoardVO;

public interface BoardDAO {

    // 모든 게시물 조회
    List<BoardVO> selectAll();

    // 특정 게시물 조회
    BoardVO selectByNo(int no);

    // 게시물 등록
    int insert(BoardVO board);

    // 게시물 수정
    boolean update(BoardVO board);

    // 게시물 삭제
    boolean delete(int no);

    // 게시물 조회수 증가
    void increaseViewCnt(int no);

    // 특정 작성자의 게시물 조회
    List<BoardVO> selectByWriter(String writer);
    
    int selectTotalcount();
    
    List<BoardVO> getBoardList(int offset, int pageSize);
}
