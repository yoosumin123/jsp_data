package sec03.brd01;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();       // 서비스 지원해달라 -> DAO 
	}

	// 일반적인 이름 list  // 쿼리이름 -> select...
	public List<ArticleVO> listArticles() { 
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(); // boardDAO 의 selectAllArticles() 메소드 호출
		return articlesList;  // articlesList로 반환                                       
	}
} // 서비스는 연결이라 별 것 없다.
