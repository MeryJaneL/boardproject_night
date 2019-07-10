package vo;

import java.sql.Date;

public class BoardBean {
	private int BOARD_NUM;
	private String BOARD_NAME;
	private String BOARD_PASS;
	private String BOARD_SUBJECT;
	private String BOARD_CONTENT;
	private String BOARD_IMAGE;
	private int BOARD_READCOUNT;
	private Date BOARD_DATE;
	
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int board_num) {
		BOARD_NUM=board_num;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String board_name) {
		BOARD_NAME=board_name;
	}
	public String getBOARD_PASS() {
		return BOARD_PASS;
	}
	public void setBOARD_PASS(String board_pass) {
		BOARD_PASS=board_pass;
	}
	
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String board_subject) {
		BOARD_SUBJECT=board_subject;
	}

	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String board_content) {
		BOARD_CONTENT=board_content;
	}

	public String getBOARD_IMAGE() {
		return BOARD_IMAGE;
	}
	public void setBOARD_IMAGE(String board_image) {
		BOARD_IMAGE=board_image;
	}
	public int getBOARD_READCOUNT() {
		return BOARD_READCOUNT;
	}
	public void setBOARD_READCOUNT(int board_readcount) {
		BOARD_READCOUNT=board_readcount;
	}

	public Date getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(Date board_date) {
		BOARD_DATE=board_date;
	}
}
