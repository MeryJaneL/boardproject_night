package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import vo.ReplyBean;
import dao.ReplyDAO;


public class ReplyDeleteService {
	public boolean isReplyWriter(int reply_num, String pass) throws Exception{
		boolean isReplyWriter = false;
		Connection con = getConnection();
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		replyDAO.setConnection(con);
		isReplyWriter = replyDAO.isReplyWriter(reply_num, pass);
		close(con);
		
		return isReplyWriter;
	}
	
	public boolean removeReply(int reply_num) {
		boolean isremoveSuccess = false;
		Connection con = getConnection();
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		replyDAO.setConnection(con);
		int deleteCount = replyDAO.deleteReply(reply_num);
		if(deleteCount>0) {
			commit(con);
			isremoveSuccess = true;
		}
		else {
			rollback(con);
		}
		close(con);
		
		return isremoveSuccess;
	}
}
