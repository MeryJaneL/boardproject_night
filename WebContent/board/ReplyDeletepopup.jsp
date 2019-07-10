<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script language = "javascript">
function moveClose(){
	self.close();
	replyDelete.submit();
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<form action = "replyDelete.bo?page=<%=request.getParameter("page")%>&board_num=<%=request.getParameter("board_num") %>&Reply_NUM=<%=request.getParameter("Reply_NUM")%>" method = "post" name = replyDelete>
 		비밀번호 : <input type = "text" name = "Reply_PASS" id = "Reply_PASS">
 		<input type = "button" value="삭제하기" onClick="moveClose()">&nbsp;
		<input type = "button" value="닫기" onClick="self.close()">
 	</form>
</body>
</html>