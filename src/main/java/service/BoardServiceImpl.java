package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	private BoardDAO bdao; // 인터페이스 (respository)
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); // class (respository)
	}
}
