<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "vo.BoardBean" %>
<%@page import = "vo.ReplyBean" %>
<%@ page import = "java.util.*" %>


<%
	BoardBean article = (BoardBean) request.getAttribute("article");
	ArrayList<ReplyBean> replylist = (ArrayList<ReplyBean>)request.getAttribute("replyList");
	String nowPage = (String) request.getAttribute("page");
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel = "stylesheet" href = "../styles/darkstyle.css">

<script language = "javascript">
	function ReplyDeletePopup(Re_num){
		window.open("ReplyDeletepopup.jsp?board_num=<%=article.getBOARD_NUM()%>&page=<%=nowPage%>&Reply_NUM="+Re_num,"a","width = 400, height=20, left=700, top=400");		
	}
</script>

</head>
<body>
	<section id = "articleForm">
	
		<h2>글 내용 상세보기</h2>
		<section id = "basicInfoArea"> 
			제목 : <%= article.getBOARD_SUBJECT() %><br>
			첨부파일 : <%if(!(article.getBOARD_IMAGE() == null)){ %>
				<a href = "file_download.jsp?file_name=<%=article.getBOARD_IMAGE() %>"><%=article.getBOARD_IMAGE() %></a>
				<%} %>				
		</section>
		<section id = "articleContentArea">
			<img src = <%="../images/"+article.getBOARD_IMAGE()%> style = "max-width : 100%; height : auto;"><br>
			<%=article.getBOARD_CONTENT() %>
		</section>
	</section>
	<section id = "commandList">
		<a href = "boardModifyForm.bo?board_num=<%=article.getBOARD_NUM()%>&page=<%=nowPage%>">[수정]</a>
		<a href = "boardDeleteForm.bo?board_num=<%=article.getBOARD_NUM()%>&page=<%=nowPage%>">[삭제]</a>
		<a href = "boardList.bo?page=<%=nowPage %>">[목록]</a>
		&nbsp;&nbsp;
	</section>
	
	<section id = "replyshowForm">
	<table id = "replyshowtable">
	<%if(replylist!=null){ %>
		<%for(int i=0; i<replylist.size(); i++){ %>
			<tr>
				<td><%=replylist.get(i).getReply_NAME()%></td>
				<td><%=replylist.get(i).getReply_CONTENT() %></td>
				<td style = "width : 70px; padding = 0px;"><input type = "button" value = "삭제하기" onclick="ReplyDeletePopup(<%=replylist.get(i).getReply_NUM()%>)"></td>
			</tr>
		<%} %>
	<%} %>
	</table>
	</section>
	
	<section id = replywriteForm>
	<form action = "replyWrite.bo?page=<%=nowPage%>&article=<%=article.getBOARD_NUM()%>" method = "post" name = replyWrite enctype="multipart/form-data">
		<span style="font-weight:bold;">댓글달기</span>	
		<table id = "replywritetable">
			<tr>
				<td align = "right" class = "td_idpw"><label for = "Reply_NAME">아이디 : </label></td>
				<td><input type = "text" name = "Reply_NAME" id = "Reply_NAME"></td>
				<td align = "right" class = "td_idpw"><label for = "Reply_PASS">비밀번호 : </label></td>
				<td><input type = "password" name = "Reply_PASS" id = "Reply_PASS"></td>
				<td><input type = "submit" value="등록">&nbsp;&nbsp;</td>	
			</tr>
			<tr>
				<td align = "center" colspan="5"><textarea name = "Reply_CONTENT" id = "Reply_CONTENT"></textarea></td>
			</tr>
		</table>
	</form>
	</section>
</body>
</html>