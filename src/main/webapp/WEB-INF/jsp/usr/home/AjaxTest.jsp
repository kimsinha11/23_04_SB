<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8"/>

<title> Ajax test
</title>
<style>
	.rs {
		border: 2px solid black;
		margin-top: 3px;
		padding: 20px;
		font-size: 2rem;
	}
</style>

<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script>
	function callByAjax() {
		var form = document.form1;
		var num1 = form.num1.value;
		var num2 = form.num2.value;
		var action= form.action;
		
		$.get(action,{
			num1 : num1,
			num2 : num2
		},function(data){
			$('.rs').empty().append(data);
			//$('.rs').text(data);
		},'html'
					
		);
	}
</script>
<body>
<h1>Ajax Test</h1>
<form name="form1" method="get" action="doPlus">
<input type="text" placeholder ="정수 입력" name="num1" />
<input type="text" placeholder ="정수 입력" name="num2" />
<input type="submit" value="더하기" />
<input onclick="callByAjax();" type="button" value="더하기 버전2" />
</form>

 <h2>더한 결과</h2>
 <div class="rs"></div>
</body>
</head>
</html>