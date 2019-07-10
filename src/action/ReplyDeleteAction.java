package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.ReplyDeleteService;
import vo.ActionForward;

public class ReplyDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		int reply_num = Integer.parseInt(request.getParameter("Reply_NUM"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int nowArticle = Integer.parseInt(request.getParameter("board_num"));
		ReplyDeleteService replyDeleteService = new ReplyDeleteService();
		
		boolean isReplyWriter = replyDeleteService.isReplyWriter(reply_num, request.getParameter("Reply_PASS"));
		if(!isReplyWriter) {
			System.out.println("´Ô²¨ ¾Æ´Ï¿¡¿ë");
		}
		else {
			boolean isDeleteSuccess = replyDeleteService.removeReply(reply_num);
			if(!isDeleteSuccess) {
				System.out.println("»èÁ¦½ÇÆÐ");
			}
			else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.bo?page="+nowPage+"&board_num="+nowArticle);
			}
		}
		
		return forward;
	}

}
