package action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardDetailService;
import svc.ReplyListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.ReplyBean;

public class BoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		BoardDetailService boardDetailService = new BoardDetailService();
		BoardBean article = boardDetailService.getArticle(board_num);

		ReplyListService replyListService = new ReplyListService();
		ArrayList<ReplyBean> replyList = new ArrayList<ReplyBean>();
		replyList = replyListService.getReplyList(board_num);
		
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		request.setAttribute("replyList", replyList);
		forward.setPath("/board/qna_board_view.jsp");
		
		return forward;
	}

}
