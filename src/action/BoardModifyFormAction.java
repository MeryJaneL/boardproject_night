package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		

		String nowPage = request.getParameter("page");
		System.out.println("모디폼! 넘버"+board_num+"페이지:"+nowPage);
		
		BoardDetailService boardDetailService = new BoardDetailService();
		BoardBean article = boardDetailService.getArticle(board_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("article", article);
		
		request.setAttribute("page", nowPage);
		
		forward.setPath("/board/qna_board_modify.jsp");
		
		return forward;
	}
	
}
