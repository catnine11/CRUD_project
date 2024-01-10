<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, guest_book_02.model.vo.GuestBookVo" %>
<%
	List<GuestBookVo> list = (List<GuestBookVo>)request.getAttribute("books");
	// 	리스트vo를 받아올거임
	// 이름이 books인 정보 중에 listvo를 쓸거임(?) =>형변환이 필요함 
	//setattribute로 books란 이름으로 보낸게 GuestBookVo 구나
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/guestbook.css" rel="stylesheet">
<style>
</style>
</head>
<body>
<!-- 	MVC 방식 -->
	<h4 class="g-top">구디 방명록</h4>
	<div class="top-img">
	</div>
	<form action="/book" method="post" id="frm">  
<!-- 	url 정해줌 -->
		<div class="g-cont">
			<ul>
				<li class="name">
					<input name="author" type="text" placeholder="이름" class="form-control">
				</li>
				<li class="msg">
					<textarea name="content" placeholder="내용을 입력해주세요" class="form-control"></textarea>
				</li>
			</ul>
			<p class="btn btn-lg" onclick="document.getElementById('frm').submit();">방명록 남기기</p>
<!-- 						id가 frm인 ..  -->
		</div>
	</form>
	<div class="guestbook list" style="display: block;">
		<%if(list != null && list.isEmpty()==false){
							//!list.imEmpty() 와 같은 것
			//null 체크 : null, isEmpty : 비어있는지 확인(껍데기만 있는지)    //list.isEmpty()==false : list가 차 있는지
			for(GuestBookVo vo : list){%>
		<ul class="cont">
			<li>
				<p class="name"><%=vo.getAuthor() %></p>
				<p class="date"><%=vo.getReg_date() %></p>
				<p class="memo"><%=vo.getContent() %></p>
			</li>
		</ul>
		<%}
		}%>
	</div>
	<script>
	
	</script>
</body>
</html>