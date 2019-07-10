package svc;

import java.sql.Connection;

import dao.ReplyDAO;
import vo.ReplyBean;
import static db.JdbcUtil.*;

public class ReplyWriteService {
	public boolean registReply(ReplyBean replyBean) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		replyDAO.setConnection(con);
		int insertCount = replyDAO.insertReply(replyBean);
		
		if(insertCount>0) {
			System.out.println("ÄÚ¹ÔÀÌ¾ß?");
			commit(con);
			isWriteSuccess = true;
		}
		else {
			rollback(con);			
		}
		close(con);
		return isWriteSuccess;		
	}

}
