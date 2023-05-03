<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.KoreaIT.ksh.demo.vo.Member"%>
<c:set var="pageTitle" value="Pw check" />
<%@ include file="../common/head.jspf"%>
<%@ page import="com.KoreaIT.ksh.demo.vo.Rq"%>

<h1 style="text-align: center; padding: 70px 20px 0;">비밀번호 확인</h1>


<form style="text-align: center;" method="post" action="doCheckPw">
<div  style="display: inline-block;  border: 2px solid black; padding: 17px; text-align:left;">

	<div>
		아이디 : <input value="${rq.loginedMember.loginId}" type="text" name="loginId" />
	</div>
	<div>
		비밀번호 : <input class="input input-bordered input-sm w-full max-w-xs" type="text" name="loginPw"
			placeholder="비밀번호를 입력해주세요" />
	</div>

<br />
<div style="text-align: center">
<button class="btn-text-link btn btn-outline btn-xs" style="display: inline;" type="submit"> 확인</button>
	</div>
</div>
</form>
<%@ include file="../common/foot.jspf"%>