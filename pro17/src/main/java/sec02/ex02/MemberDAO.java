 package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 // listMembers() = 컨트롤러가 불러서 실행됨.  -> select문으로 모든 회원 정보 조회 
	  // 그 후에 membersList로 반응 ( 호출한 곳 )
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from  t_member order by joinDate desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query); // 미리 컴파일 해놔라 = prepare
			ResultSet rs = pstmt.executeQuery();  //조회(select)라서 쿼리
			while (rs.next()) {                  // 무한루틴
				String id = rs.getString("id");   // rs -> DB 가져오는것
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
				membersList.add(memberVO);   // 계속 더하는 것
			} 
			rs.close();
			pstmt.close();
			conn.close();   // ㄷ자 형태 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;     // membersList에 저장 후 반환. 다 모은 것 JSP 로 넘겨줌
	}

	
	// 회원 정보 추가 기능
	// 역할 - 등록창에서 입력한 정보를 멤버 부여 속성에 세팅 후 넘기는 것
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection(); // 넘기면 get을 써서 가져온 후 insert문으로 추가. 
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			String query = "INSERT INTO t_member(id, pwd, name, email)" + " VALUES(?, ? ,? ,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();   // 테이블을 바꾸니까 update
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public MemberVO findMember(String _id) {
		MemberVO memInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from  t_member where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _id);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			memInfo = new MemberVO(id, pwd, name, email, joinDate);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	public void modMember(MemberVO memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_member set pwd=?,name=?,email=?  where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from t_member where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
