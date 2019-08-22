<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="../includes/headerForBoard.jsp"%>

<title>글쓰기</title>

	
</head>
<body>

<p class="center jumbotron jumbotron-fluid"
		style="font-weight: 1000; font-size: 1.5em; background-color: #475C7A;
		color: white; text-align: center;" > 문의글 작성</p> 

<div style="width: 60%; margin: auto;">
	<form method="post" action="/board/register">
		<p>${member.name }&nbsp; 님</p>
		<!-- 로그인 만든 후 작성자는 hidden 처리 -->
		<input type="text" name="title" style="width: 100%;" placeholder="제목"/><br>
		<input type="hidden" name="writer" value="${member.name }"/><br>
		<input type="hidden" name="email" value="${member.email }"/><br>
		<textarea id="summernote" name="content"></textarea>
		<input id="subBtn" type="button" value="글 작성" class="btn btn-secondary" style="float: right;" onclick="register(this.form)" /> 
	</form>
</div>

</body>



<script>
$(document).ready(function() {
	  $('#summernote').summernote({
 	    	placeholder: 'content',
	        minHeight: 370,
	        maxHeight: null,
	        focus: true, 
	        lang : 'ko-KR'
	  });
	});
	
function register(frm) {
	var title = frm.title.value;
	var writer = frm.writer.value;
	var content = frm.content.value;
	var email = frm.email.value;
	if (title.trim() == ''){
		alert("제목을 입력해주세요");
		return false;
	}
	if (writer.trim() == ''){
		alert("작성자를 입력해주세요");
		return false;
	}
	if (content.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	}
	frm.submit();
}
</script>
