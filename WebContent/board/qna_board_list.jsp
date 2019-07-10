<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.PageInfo" %>
<%@ page import = "vo.BoardBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
	ArrayList<BoardBean> articleList = (ArrayList<BoardBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel = "stylesheet" href = "../styles/darkstyle.css">

</head>
<body>
	<section id="listForm">
		<h2>글목록&nbsp;&nbsp;<a href="boardWriteForm.bo">게시판글쓰기</a></h2>
			<table id = "listtable">
			<%if(articleList != null && listCount>0){%>
				<tr id ="tr_top">
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
				<%for(int i=0; i<articleList.size(); i++){ %>
					<tr class = "articles">
						<td><%=articleList.get(i).getBOARD_NUM() %></td>
						<td>
							▶<a href = "boardDetail.bo?board_num=<%=articleList.get(i).getBOARD_NUM()%>&page=<%=nowPage%>"><%=articleList.get(i).getBOARD_SUBJECT()%></a>
						</td>
						<td><%=articleList.get(i).getBOARD_NAME() %></td>
						<td><%=articleList.get(i).getBOARD_DATE() %></td>
						<td><%=articleList.get(i).getBOARD_READCOUNT() %></td>
					</tr>
				<%} %>	
			</table>
	</section>
	
	<section id="pageList" style ="color : gray;">
		<%if(nowPage<=1){ %>
			[이전]&nbsp;
		<%}else {%>
			<a href="boardList.bo?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
		<%} %>	
		
		<%for(int a = startPage; a<=endPage; a++){ 
			if(a==nowPage){ %>
				[<%=a%>]
			<%}else{%>
				<a href = "boardList.bo?page=<%=a %>">[<%=a %>]</a>&nbsp;
			<%} %>	
		<%} %>	
			
		<%if(nowPage>=maxPage){ %>
			[다음]
		<%} else {%>
			<a href="boardList.bo?page=<%=nowPage+1 %>">[다음]</a>
		<%} %>	
	</section>
	
		<%} else {%>
			<section id="emptyArea">등록된 글이 없습니다.</section>
		<%} %>	
</body>
</html>