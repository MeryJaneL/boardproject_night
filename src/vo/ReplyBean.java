package vo;

public class ReplyBean {
	private int Reply_NUM;
	private int Reply_WHERE;
	private String Reply_NAME;
	private String Reply_PASS;
	private String Reply_CONTENT;
	
	public int getReply_NUM() {
		return Reply_NUM;
	}
	public void setReply_NUM(int Reply_num) {
		Reply_NUM=Reply_num;
	}
	public int getReply_WHERE() {
		return Reply_WHERE;
	}
	public void setReply_WHERE(int Reply_where) {
		Reply_WHERE=Reply_where;
	}
	public String getReply_NAME() {
		return Reply_NAME;
	}
	public void setReply_NAME(String Reply_name) {
		Reply_NAME=Reply_name;
	}
	public String getReply_PASS() {
		return Reply_PASS;
	}
	public void setReply_PASS(String Reply_pass) {
		Reply_PASS=Reply_pass;
	}
	public String getReply_CONTENT() {
		return Reply_CONTENT;
	}
	public void setReply_CONTENT(String Reply_content) {
		Reply_CONTENT=Reply_content;
	}
}