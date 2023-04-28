<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.KoreaIT.ksh.demo.vo.Article"%>
<c:set var="pageTitle" value="DETAIL" />
<%@ include file="../common/head.jspf"%>
<!-- <iframe src="http://localhost:8081/usr/article/doIncreaseHitCountRd?id=2" frameborder="0"></iframe> -->
<script>
	const params = {}
	params.id = parseInt('${param.id}');
</script>
<script>
	function ArticleDetail__increaseHitCount() {
		const localStorageKey = 'article__' + params.id + '__alreadyView';
		if (localStorage.getItem(localStorageKey)) {
			return;
		}

		localStorage.setItem(localStorageKey, true);

		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}
	$(function() {
		//실전
		ArticleDetail__increaseHitCount();
		//연습
		//setTimeout(ArticleDetail__increaseHitCount, 2000);
	})
</script>
<%
Article article = (Article) request.getAttribute("article");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>
<section class="mt-10 text-xl">
	<div class="mx-auto overflow-x-auto">
		<table class=" table w-full table-box-type-1"
			style="width: 500px; height: 500px;">
			<thead>
				<tr>
					<th style="font-size: 15px">번호</th>
					<th>
						<div class="badge badge-lg">${article.id }</div>
					</th>
				</tr>
				<tr>
					<th style="font-size: 15px">작성날짜</th>
					<th>${article.regDate.substring(0,10) }</th>
				</tr>
				<tr>
					<th style="font-size: 15px">수정날짜</th>
					<th>${article.updateDate.substring(0,10) }</th>
				</tr>
				<tr>
					<th style="font-size: 15px">작성자</th>
					<th>${article.name }</th>
				</tr>
				<tr>
					<th style="font-size: 15px">제목</th>
					<th>${article.title }</th>
				</tr>
				<tr>
					<th style="font-size: 15px">내용</th>
					<th>${article.body }</th>
				</tr>
				<tr>
					<th style="font-size: 15px">조회수</th>
					<th><span class="article-detail__hit-count">${article.hitCount }</span>
					</th>
				</tr>

			</thead>

		</table>

	</div>
	<div class="btns">
		<div style="text-align: center">
			<%
			if (article.getMemberId() != loginedMemberId) {
			%>
			<button class="btn-text-link btn btn-outline btn-xs" type="button"
				onclick="history.back()">뒤로가기</button>
			<%
			}
			%>
			<%
			if (rq.isLogined()) {
			%>


			<a
				href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri }"
				class="btn btn-outline" type="button"> <i
				class="liked-icon fa-heart fas fa-lg"></i><span
				id="LikeCount_${article.id}">${article.goodReactionPoint}</span>
			</a> <a
				href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.encodedCurrentUri }"
				class="btn btn-outline" type="button"> <i
				class="liked-icon fa-heart far fa-lg"></i> <span
				id="DisLikeCount_${article.id}">${article.badReactionPoint}</span>
			</a>

			<div class="reaction-buttons">
				<a href="#" class="reaction-button good-button"> <i
					class="fa fa-heart-o"></i> <i class="fa fa-heart"></i> <span
					class="reaction-count">0</span>
				</a> <a href="#" class="reaction-button bad-button"> <i
					class="fa fa-thumbs-o-down"></i> <i class="fa fa-thumbs-down"></i>
					<span class="reaction-count">0</span>
				</a>
			</div>

			<%
			}
			%>
		</div>
		<div style="text-align: center">
			<%
			if (article.getMemberId() == loginedMemberId) {
			%>
			<button class="btn-text-link btn btn-outline btn-xs" type="button"
				onclick="location.href='list'">뒤로가기</button>
			<a class="btn-text-link btn btn-outline btn-xs"
				onclick="if(confirm('정말 수정하시겠습니까?') == false) return false;"
				href="modify?id=${article.id }">수정</a> <a
				class="btn-text-link btn btn-outline btn-xs"
				onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;"
				href="delete?id=${article.id }">삭제</a>
			<%
			}
			%>
		</div>
	</div>


	<script>
	const goodButton = document.querySelector('.good-button');
	const badButton = document.querySelector('.bad-button');

	goodButton.addEventListener('click', () => {
	  goodButton.classList.toggle('liked');
	  const countEl = goodButton.querySelector('.reaction-count');
	  const count = parseInt(countEl.textContent);
	  if (goodButton.classList.contains('liked')) {
	    goodButton.querySelector('.fa-heart-o').style.display = 'none';
	    goodButton.querySelector('.fa-heart').style.display = 'inline-block';
	    countEl.textContent = count + 1;
	  } else {
	    goodButton.querySelector('.fa-heart-o').style.display = 'inline-block';
	    goodButton.querySelector('.fa-heart').style.display = 'none';
	    countEl.textContent = count - 1;
	  }
	});

	badButton.addEventListener('click', () => {
	  badButton.classList.toggle('disliked');
	  const countEl = badButton.querySelector('.reaction-count');
	  const count = parseInt(countEl.textContent);
	  if (badButton.classList.contains('disliked')) {
	    badButton.querySelector('.fa-thumbs-o-down').style.display = 'none';
	    badButton.querySelector('.fa-thumbs-down').style.display = 'inline-block';
	    countEl.textContent = count + 1;
	  } else {
	    badButton.querySelector('.fa-thumbs-o-down').style.display = 'inline-block';
	    badButton.querySelector('.fa-thumbs-down').style.display = 'none';
	    countEl.textContent = count - 1;
	  }
	});
</script>
</section>



<%@ include file="../common/foot.jspf"%>