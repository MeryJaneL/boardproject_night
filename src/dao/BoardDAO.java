package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.BoardBean;

public class BoardDAO {
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() {
		
	}
	
	public static BoardDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select count(*) from MJboard");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}
		catch(Exception ex) {
			System.out.println("getListCount 에러 : "+ex);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<BoardBean> selectArticleList (int page, int limit){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from MJboard order by BOARD_NUM desc limit ?,10";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startrow = (page-1)*10;
		
		try {
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_IMAGE(rs.getString("BOARD_IMAGE"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				articleList.add(board);
			}
		}
		catch(Exception e) {
			System.out.println("getboardList 에러 : "+e);
		}
		finally{
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	public BoardBean selectArticle(int board_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean board = null;
		
		try {
			pstmt = con.prepareStatement("select * from MJboard where BOARD_NUM = ?");
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_IMAGE(rs.getString("BOARD_IMAGE"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));				
			}
		}
		catch(Exception e) {
			System.out.println("getdetail 에러 : "+e);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}
	public int insertArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "";
		int insertCount = 0;
		
		try {
			pstmt = con.prepareStatement("select max(board_num) from MJboard");
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1)+1;	
			else
				num = 1;
			sql = "insert into MJboard (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_IMAGE, BOARD_READCOUNT, BOARD_DATE) values(?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBOARD_NAME());
			pstmt.setString(3, article.getBOARD_PASS());
			pstmt.setString(4, article.getBOARD_SUBJECT());
			pstmt.setString(5, article.getBOARD_CONTENT());
			pstmt.setString(6, article.getBOARD_IMAGE());
			pstmt.setInt(7, 0);
			
			insertCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("boardInsert 에러 :"+e);
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
	public int updateArticle(BoardBean article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update MJboard set BOARD_SUBJECT=?, BOARD_CONTENT=? where BOARD_NUM=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getBOARD_SUBJECT());
			pstmt.setString(2, article.getBOARD_CONTENT());
			pstmt.setInt(3, article.getBOARD_NUM());
			updateCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("boardModify 에러 : "+e);
		}
		finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	public int deleteArticle(int board_num) {
		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from MJboard where BOARD_NUM=?";
		int deleteCount=0;
		
		try {
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, board_num);
			deleteCount=pstmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("boardDelete 에러 : "+e);
		}
		finally {
			close(pstmt);
		}
		return deleteCount;
	}
	public int updateReadCount(int board_num) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update MJboard set BOARD_READCOUNT = BOARD_READCOUNT+1 where BOARD_NUM ="+board_num;
		
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("setreadcountupdate에러 : "+e);
		}
		finally {
			close(pstmt);
		}
		return updateCount;
	}
	public boolean isArticleBoardWriter(int board_num, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String board_sql = "select* from MJboard where BOARD_NUM=?";
		boolean isWriter = false;
		
		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			rs.next();
			
			if(pass.equals(rs.getString("BOARD_PASS"))) {
				isWriter = true;
			}
		}
		catch(SQLException e) {
			System.out.println("isboardwriter 에러 : "+e);
		}
		finally {
			close(pstmt);			
		}
		
		return isWriter;
	}
}
