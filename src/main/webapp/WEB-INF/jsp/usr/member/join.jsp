<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="pageTitle" value="JOIN" />
<%@ include file="../common/head.jspf"%>

<br /><br /><br />
<script>
	let submitJoinFormDone = false;
	let validLoginId ="";
	function submitJoinForm(form) {
		if (submitJoinFormDone) {
			alert('처리중입니다');
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value == 0) {
			alert('아이디를 입력해주세요');
			form.loginId.focus();
			return;
		}
		if (form.loginId.value != validLoginId) {
			alert('사용할 수 없는 아이디입니다');
			form.loginId.focus();
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value == 0) {
			alert('비밀번호를 입력해주세요');
			form.loginPw.focus();
			return;
		}
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value == 0) {
			alert('비밀번호 확인을 입력해주세요');
			form.loginPw.focus();
			return;
		}
		if (form.loginPwConfirm.value != form.loginPw.value) {
			alert('비밀번호가 일치하지 않습니다');
			form.loginPw.focus();
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value == 0) {
			alert('이름을 입력해주세요');
			return;
		}
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value == 0) {
			alert('닉네임을 입력해주세요');
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value == 0) {
			alert('이메일을 입력해주세요');
			return;
		}
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value == 0) {
			alert('전화번호를 입력해주세요');
			return;
		}
		submitJoinFormDone = true;
		form.submit();
	}
	
	function checkLoginIdDup(el) {
		$('.checkDup-msg').empty();
		const form = $(el).closest('form').get(0);
		
		if(form.loginId.value.length == 0) {
			validLoginId ='';
			return;
		}
		$.get('../member/getLoginIdDup', {
			isAjax : 'Y',
			loginId : form.loginId.value
		}, function(data) {
			$('.checkDup-msg').html('<div>' + data.msg + '</div>')
			if(data.success){
				validLoginId = data.data1;
			} else {
				validLoginId = '';
			}
		}, 'json');
	}
</script>
<form style="text-align: center;" method="post" onsubmit="submitJoinForm(this); return false;" action="doJoin">
	<div style="display: inline-block; border: 2px solid black; padding: 50px; text-align: left;">
		<div>
			아이디 :
			<input onkeyup="checkLoginIdDup(this);"class="input input-bordered input-sm w-full max-w-xs" type="text" name="loginId" placeholder="아이디를 입력해주세요"  id="loginId" />
			<div style="font-size:12px; color:blue;"class="checkDup-msg"></div>
		</div>
		<div>
			비밀번호 :
			<input class="input input-bordered input-sm w-full max-w-xs" type="text" name="loginPw" placeholder="비밀번호를 입력해주세요" autocomplete="off"/>
		</div>
		<div>
			이름 :
			<input class="input input-bordered input-sm w-full max-w-xs" type="text" name="name" placeholder="이름을 입력해주세요" />
		</div>
		<div>
			닉네임 :
			<input class="input input-bordered input-sm w-full max-w-xs" type="text" name="nickname" placeholder="닉네임을 입력해주세요" />
		</div>
		<div>
			전화번호 :
			<input class="input input-bordered input-sm w-full max-w-xs" value="" type="text" name="cellphoneNum" placeholder="전화번호를 입력해주세요" autocomplete="off"/>
		</div>
		<div>
			이메일 :
			<input class="input input-bordered input-sm w-full max-w-xs" value="" type="text" name="email" placeholder="이메일을 입력해주세요" />
		</div>
		<br />
		<div style="text-align: center">
			<button class="btn-text-link btn btn-outline btn-xs" style="display: inline;" type="submit">회원가입</button>
			<a class="btn-text-link btn btn-outline btn-xs" href="/usr/member/login">로그인</a>
		</div>
	</div>
</form>

<%@ include file="../common/foot.jspf"%>