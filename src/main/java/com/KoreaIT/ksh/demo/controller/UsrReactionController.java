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


	@RequestMapping("/usr/reaction/doIncreaseGoodCountRd")
	public ResultData doIncreaseGoodCountRd(int id) {
		ResultData doIncreaseGoodCountRd = reactionService.doIncreaseGoodCount(id);
		
		ResultData rd = ResultData.newData(doIncreaseGoodCountRd, "point", reactionService.getReactionGoodCount(id));
		rd.setData2("id", id);
		return rd;
	}


}
