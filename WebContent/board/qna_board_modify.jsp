<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.BoardBean" %>

<%
	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type = "text/javascript">
		function modifyboard(){
			modifyform.submit();
		}
	</script>

<link rel = "stylesheet" href = "../styles/darkstyle.css">
	
</head>
<body>
	<section id = "modifyForm">
		<h2>글수정</h2>
		<form action = "boardModifyPro.bo?page=<%=nowPage%>" method="post" name = "modifyform">
			<input type = "hidden" name = "BOARD_NUM" value = "<%=article.getBOARD_NUM() %>"/>
			<table>
				<tr>
					<td class = "td_left"><label for = "BOARD_NAME">글쓴이</label></td>
					<td class = "td_right"><input type = "text" name = "BOARD_NAME" id = "BOARD_NAME" value = "<%=article.getBOARD_NAME()%>+<%=nowPage%>"/></td>
				</tr>
				<tr>
					<td class = "td_left"><label for = "BOARD_PASS">비밀번호</label></td>
					<td class = "td_right"><input type = "password" name = "BOARD_PASS" id = "BOARD_PASS"/></td>
				</tr>
				<tr>
					<td class = "td_left"><label for = "BOARD_SUBJECT">제목</label></td>
					<td class = "td_right"><input type = "text" name = "BOARD_SUBJECT" id = "BOARD_SUBJECT" value = "<%=article.getBOARD_SUBJECT()%>"/></td>
				</tr>
				<tr>
					<td class = "td_left"><label for = "BOARD_CONTENT">내용</label></td>
					<td class = "td_right"><textarea name = "BOARD_CONTENT" id = "BOARD_CONTENT"><%=article.getBOARD_CONTENT()%></textarea></td>
				</tr>
			</table>
			<section id = "commandCell">
				<a href = "javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
				<a href = "javascript:history.go(-1)">[뒤로]</a>
			</section>
		</form>
	</section>
</body>
</html>