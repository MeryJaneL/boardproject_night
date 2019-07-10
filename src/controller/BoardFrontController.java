package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import action.*;
import vo.ActionForward;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		System.out.println("1st : "+command);
		
		if(command.equals("/board/boardWriteForm.bo")) {
			forward = new ActionForward();
			forward.setPath("/board/qna_board_write.jsp");
		}
		else if(command.equals("/board/boardWritePro.bo")) {
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/boardList.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/boardDetail.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/boardModifyForm.bo")) {
			String nowPage = request.getParameter("page");
			System.out.println("페이지는모디폼 ! : "+nowPage);
			request.setAttribute("page", nowPage);
			
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/boardModifyPro.bo")) {
			String nowPage = request.getParameter("page");
			System.out.println("페이지는모디프로 ! : "+nowPage);
			request.setAttribute("page", nowPage);
			
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/boardDeleteForm.bo")) {
			String nowPage = request.getParameter("page");
			request.setAttribute("page", nowPage);
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num", board_num);
			forward=new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		}
		else if(command.equals("/board/boardDeletePro.bo")) {
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		

		else if(command.equals("/board/replyWrite.bo")) {
			action = new ReplyWriteAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/board/replyDelete.bo")) {
			System.out.println("이젠 이거냐 먼저"+request.getParameter("page"));
			System.out.println("이젠 이거냐 먼저"+request.getParameter("board_num"));
			System.out.println("이젠 이거냐 먼저"+request.getParameter("Reply_NUM"));
			System.out.println("이젠 이거냐 먼저"+request.getParameter("Reply_PASS"));
			action = new ReplyDeleteAction();
			try {
				forward = action.execute(request, response);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doProcess(request, response);
	}

}
