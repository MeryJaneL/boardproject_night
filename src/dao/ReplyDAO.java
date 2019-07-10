package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.ReplyBean;

public class ReplyDAO {
	DataSource ds;
	Connection con;
	private static ReplyDAO replyDAO;
	
	private ReplyDAO() {
		
	}
	
	public static ReplyDAO getInstance() {
		if(replyDAO == null) {
			replyDAO = new ReplyDAO();
		}
		return replyDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int selectReplyCount(int board_num) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select count(*) from MJreply where Reply_WHERE = ?");
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}
		catch(Exception ex) {
			System.out.println("getReplyCount 에러 : "+ex);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<ReplyBean> selectReplyList(int where){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String reply_list_sql = "select * from MJreply where Reply_WHERE = ? order by Reply_NUM asc";
		ArrayList<ReplyBean> replylist = new ArrayList<ReplyBean>();
		ReplyBean reply = null;
		
		try {
			pstmt = con.prepareStatement(reply_list_sql);
			pstmt.setInt(1, where);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reply = new ReplyBean();
				reply.setReply_NUM(rs.getInt("Reply_NUM"));
				reply.setReply_WHERE(rs.getInt("Reply_WHERE"));
				reply.setReply_NAME(rs.getString("Reply_NAME"));
				reply.setReply_PASS(rs.getString("Reply_PASS"));
				reply.setReply_CONTENT(rs.getString("Reply_CONTENT"));
				replylist.add(reply);
			}
		}
		catch(Exception e) {
			System.out.println("selectReplyList 에러 : "+e);
		}
		finally{
			close(rs);
			close(pstmt);
		}
		
		return replylist;
	}	
	
	public ReplyBean selectReply(int reply_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyBean reply = null;
		
		try {
			pstmt = con.prepareStatement("select * from MJreply where Reply_NUM = ?");
			pstmt.setInt(1, reply_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reply = new ReplyBean();
				reply.setReply_NUM(rs.getInt("Reply_NUM"));
				reply.setReply_WHERE(rs.getInt("Reply_WHERE"));
				reply.setReply_NAME(rs.getString("Reply_WHERE"));
				reply.setReply_PASS(rs.getString("Reply_PASS"));
				reply.setReply_CONTENT(rs.getString("Reply_CONTENT"));
			}
		}
		catch(Exception e) {
			System.out.println("selectReply 에러 : "+e);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return reply;
	}
	public int insertReply(ReplyBean reply) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount = 0;
		
		try {
			pstmt = con.prepareStatement("select max(Reply_NUM) from MJreply");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;	
			else
				num = 1;
			sql = "insert into MJreply (REPLY_NUM, REPLY_WHERE ,REPLY_NAME, REPLY_PASS, REPLY_CONTENT) values(?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, reply.getReply_WHERE());
			pstmt.setString(3, reply.getReply_NAME());
			pstmt.setString(4, reply.getReply_PASS());
			pstmt.setString(5, reply.getReply_CONTENT());
						
			insertCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("replyInsert 에러 :"+e);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int updateReply(ReplyBean reply) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update MJreply set Reply_CONTENT=? where Reply_NUM=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reply.getReply_CONTENT());
			pstmt.setInt(2, reply.getReply_NUM());
			updateCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("replyModify 에러 : "+e);
		}
		finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deleteReply(int reply_num) {
		PreparedStatement pstmt = null;
		String reply_delete_sql = "delete from MJreply where Reply_NUM=?";
		int deleteCount=0;
		
		try {
			pstmt = con.prepareStatement(reply_delete_sql);
			pstmt.setInt(1, reply_num);
			deleteCount=pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("replyDelete 에러 : "+e);
		}
		finally {
			close(pstmt);
		}
		return deleteCount;
	}
	public boolean isReplyWriter(int reply_num, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String reply_sql = "select * from MJreply where Reply_NUM=?";
		boolean isWriter = false;
		
		try {
			pstmt = con.prepareStatement(reply_sql);
			pstmt.setInt(1, reply_num);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(pass.equals(rs.getString("Reply_PASS"))) {
				isWriter = true;
			}
		}
		catch(SQLException e) {
			System.out.println("isreplywriter 에러 : "+e);
		}
		finally {
			close(pstmt);			
		}
		
		return isWriter;
	}
}
