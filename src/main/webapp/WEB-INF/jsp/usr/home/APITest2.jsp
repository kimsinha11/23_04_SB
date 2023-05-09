<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="API Test2"></c:set>
<script>
	const API_KEY = '';
async function getData() {
		
	const url='http://apis.data.go.kr/1180000/DaejeonNationalCemetery/Burialinfo042?name=홍길동&pageNo=1&numOfRows=50&serviceKey='+ API_KEY;
	const response = await fetch(url);
	const data = await response.json();
	console.log("dat", data);
}
getData();
</script>
<%@ include file="../common/foot.jspf"%>