package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import dao.ReplyDAO;
import vo.ReplyBean;

public class ReplyListService {
	public ArrayList<ReplyBean> getReplyList(int where) throws Exception{
		ArrayList<ReplyBean> replyList = null;
		Connection con = getConnection();
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		replyDAO.setConnection(con);
		replyList = replyDAO.selectReplyList(where);
		close(con);
		return replyList;
	}
}
