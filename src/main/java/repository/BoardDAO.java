package repository;

import domain.Board;
import java.util.List;

public interface BoardDAO {
    // 모든 게시물 조회
    List<Board> selectAll();
    
    // 특정 게시물 조회
    Board selectById(int boardId);
    
    // 게시물 등록
    int insert(Board board);
    
    // 게시물 수정
    int update(Board board);
    
    // 게시물 삭제
    int delete(int boardId);
}
