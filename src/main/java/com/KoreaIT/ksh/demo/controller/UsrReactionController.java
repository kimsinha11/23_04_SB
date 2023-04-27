package com.KoreaIT.ksh.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.ksh.demo.service.ArticleService;
import com.KoreaIT.ksh.demo.service.BoardService;
import com.KoreaIT.ksh.demo.service.ReactionService;
import com.KoreaIT.ksh.demo.util.Ut;
import com.KoreaIT.ksh.demo.vo.Article;
import com.KoreaIT.ksh.demo.vo.Board;
import com.KoreaIT.ksh.demo.vo.ResultData;
import com.KoreaIT.ksh.demo.vo.Rq;

@Controller
public class UsrReactionController {

	@Autowired
	private ReactionService reactionService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private Rq rq;


	@RequestMapping("/usr/reaction/doGoodPoint")
	@ResponseBody
	public ResultData<?> doGoodPoint(int id, int memberId) {

		ResultData<?> GoodPointRd = reactionService.GoodPointRd(id, memberId);

		if (GoodPointRd.isFail()) {
			return GoodPointRd;
		}

		int goodCount = reactionService.getArticleGoodCount(id);

		ResultData<?> rd = ResultData.from(GoodPointRd.getResultCode(), GoodPointRd.getMsg(), "likeCount", goodCount);

		return rd;
	}

	@RequestMapping("/usr/reaction/doCancelGoodPoint")
	@ResponseBody
	public ResultData<?> doCancelGoodPoint(int id, String relTypeCode, int memberId) {

	    ResultData<?> cancelGoodPointRd = reactionService.doCancelGoodPoint(id, relTypeCode, memberId);

	    return cancelGoodPointRd;
	}
	
	@RequestMapping("/usr/reaction/doBadPoint")
	@ResponseBody
	public ResultData<?> doBadPoint(int id, int memberId) {

		ResultData<?> BadPointRd = reactionService.BadPointRd(id, memberId);

		if (BadPointRd.isFail()) {
			return BadPointRd;
		}

		int badCount = reactionService.getArticleBadCount(id);

		ResultData<?> rd = ResultData.from(BadPointRd.getResultCode(), BadPointRd.getMsg(), "likeCount", badCount);

		return rd;
	}
}
