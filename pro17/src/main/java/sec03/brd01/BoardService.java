package sec03.brd01;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO(); // 서비스 지원해달라 -> DAO 
	}

	public List<ArticleVO> listArticles() { // 일반적인 이름 list
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(); // 쿼리이름 -> select...
		return articlesList;                                         // 
	}
} // 서비스는 연결이라 별 것 없다.
