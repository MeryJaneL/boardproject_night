package action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ReplyWriteService;
import vo.ActionForward;
import vo.ReplyBean;

public class ReplyWriteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		ReplyBean reply = null;
				
		int nowPage = Integer.parseInt(request.getParameter("page"));
		int nowarticle = Integer.parseInt(request.getParameter("article"));
		
		String realFolder="";
		String saveFolder="/images";
		int fileSize = 5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
				
		
		reply = new ReplyBean();
		reply.setReply_WHERE(nowarticle);
		reply.setReply_NAME(multi.getParameter("Reply_NAME"));
		reply.setReply_PASS(multi.getParameter("Reply_PASS"));
		reply.setReply_CONTENT(multi.getParameter("Reply_CONTENT"));
		ReplyWriteService replyWriteService = new ReplyWriteService();
		boolean isWriteSuccess = replyWriteService.registReply(reply);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
		}
		else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardDetail.bo?board_num="+nowarticle+"&page="+nowPage);			
		}
		
		return forward;
	}
}
