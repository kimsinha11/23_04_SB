<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.KoreaIT.ksh.demo.vo.Comment"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<c:set var="pageTitle" value="ARTICLE MODIFY" />
<%@ include file="../common/head.jspf"%>
<%
Comment comment = (Comment) request.getAttribute("comment");
%>

<h1 style="text-align: center; padding: 70px 20px 0;">${comment.id }번
	댓글 수정</h1>


<form style="text-align: center;" method="post" action="docModify">
<div  style="display: inline-block;  border: 2px solid black; padding: 17px; text-align:left;">
	<div>
		번호 : <input value="${comment.id }" class="input input-bordered w-full max-w-xs"  type="hidden" name="id"
			/>
	</div>
		<div>
		게시물번호 : <input value="${comment.relId }" class="input input-bordered w-full max-w-xs"  type="hidden" name="relId"
			/>
	</div>
	<div>작성날짜 : ${comment.regDate }</div>
	<div>
		내용 :
		<textarea type="text" class="input input-bordered w-full max-w-xs"  placeholder="내용을 입력해주세요" name="body" /></textarea>
	</div>

	<button class="btn-text-link btn btn-outline btn-xs" style="display: inline" type="submit"> 수정하기</button>
		</div>
</form>
<%@ include file="../common/foot.jspf"%>