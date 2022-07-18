package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MemberController
 */
//@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	public void init() throws ServletException {
		memberDAO = new MemberDAO(); // 모델에 해당하는 멤버 DAO 객체 생성 - DAO 작업한 것 쓰려고
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	// private  => 여기만 있을 것이다 (doHandle)
	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo(); // 브라우저에서 요청한 요청명을 얻음. /addmember.do 등...
		System.out.println("action:" + action);
		// 최초 요청이거나 /listMembers.do 면 회원 정보 출력 하라 
		if (action == null || action.equals("/listMembers.do")) {
		// 컨트롤러가 요청을 받아서 memberDAO의 listMembers() 메소드 호출 후 다시 membersList로 회원 정보 받기.
			List<MemberVO> membersList = memberDAO.listMembers(); 
			request.setAttribute("membersList", membersList); // request = 사용자의 요청 
			nextPage = "/test03/listMembers.jsp"; // test03의 listMemebers.jsp로 포워딩
		} else if (action.equals("/addMember.do")) { // 회원 정보 추가를 요청할 시
			 // 회원가입 창에서 입력한 정보를 getParemeter로 얻는다.
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			// getParameter로 얻은 후 MemberVO 세팅 (id, pwd, name, email)
			MemberVO memberVO = new MemberVO(id, pwd, name, email); 
			// MemberVO(id, pwd, name, email) 세팅 후 memberDAO.addMember() 메소드로 전달.
			memberDAO.addMember(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/member/listMembers.do";    // 회원 정보 추가 페이지로 이동.
		} else if (action.equals("/memberForm.do")) { // 회원 가입 창을 요청
			nextPage = "/test03/memberForm.jsp"; // DAO 연동 로직은 없고 회원가입 창으로 이동
		}else if(action.equals("/modMemberForm.do")){
		     String id=request.getParameter("id");
		     MemberVO memInfo = memberDAO.findMember(id);
		     request.setAttribute("memInfo", memInfo);
		     nextPage="/test03/modMemberForm.jsp";
		}else if(action.equals("/modMember.do")){
		     String id=request.getParameter("id");
		     String pwd=request.getParameter("pwd");
		     String name= request.getParameter("name");
	         String email= request.getParameter("email");
		     MemberVO memberVO = new MemberVO(id, pwd, name, email);
		     memberDAO.modMember(memberVO);
		     request.setAttribute("msg", "modified");
		     nextPage="/member/listMembers.do";
		}else if(action.equals("/delMember.do")){
		     String id=request.getParameter("id");
		     memberDAO.delMember(id);
		     request.setAttribute("msg", "deleted");
		     nextPage="/member/listMembers.do";
		     // action 값이 일치하지 않을 경우 회원 목록 창을 보여줌.
		}else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		// 각각의 if else 문에서 세팅된 nextPage를 입력해서 포워딩
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage); 
		dispatch.forward(request, response);
	}

}
