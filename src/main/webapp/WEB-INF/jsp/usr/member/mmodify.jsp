<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.KoreaIT.ksh.demo.vo.Member"%>
<c:set var="pageTitle" value="PROFILE" />
<%@ include file="../common/head.jspf"%>

<%
Member member = (Member) request.getAttribute("member");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<h1 style="text-align: center; padding: 70px 20px 0;">${member.name }번
	내정보 수정</h1>


<form style="text-align: center;" method="post" action="domModify">
<div  style="display: inline-block;  border: 2px solid black; padding: 17px; text-align:left;">
	<div>
		번호 : <input value="${member.id }" class="input input-bordered w-full max-w-xs"  type="hidden" name="id"
			/>
	</div>
	<div>가입날짜 : ${member.regDate }</div>
	<div>
		이름 : <input value="${member.name }" class="input input-bordered w-full max-w-xs"  type="text" name="name"
			placeholder="이름을 입력해주세요" />
	</div>
	<div>
		닉네임 : <input value="${member.nickname }" class="input input-bordered w-full max-w-xs"  type="text" name="nickname"
			placeholder="닉네임을 입력해주세요" />
	</div>
	<div>
		전화번호 : <input value="${member.cellphoneNum }" class="input input-bordered w-full max-w-xs"  type="text" name="cellphoneNum"
			placeholder="전화번호를 입력해주세요" />
	</div>
	<div>
		이메일 : <input value="${member.email }" class="input input-bordered w-full max-w-xs"  type="text" name="email"
			placeholder="이메일을 입력해주세요" />
	</div>

	<button class="btn-text-link btn btn-outline btn-xs" style="display: inline" type="submit"> 수정하기</button>
		</div>
</form>

<%@ include file="../common/foot.jspf"%>