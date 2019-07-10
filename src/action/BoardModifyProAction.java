package action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isModifySuccess = false;
		int board_num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		
		String page = request.getParameter("page");
		System.out.println("�ѹ�"+board_num+"������:"+page);
		
		BoardBean article = new BoardBean();
		BoardModifyProService boardModifyProService = new BoardModifyProService();
		boolean isRightUser = boardModifyProService.isArticleWriter(board_num, request.getParameter("BOARD_PASS"));
		
		if(!isRightUser) {
			System.out.println("�ϲ� �ƴմϴ�");
		}
		else {
			article.setBOARD_NUM(board_num);
			article.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			article.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			isModifySuccess = boardModifyProService.modifyArticle(article);
			
			if(!isModifySuccess) {
				System.out.println("�������п���");
			}
			else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardDetail.bo?board_num="+article.getBOARD_NUM()+"&page="+page);
			}
		}
		
		return forward;
	}
	
}
