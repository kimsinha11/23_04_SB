package com.KoreaIT.ksh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KoreaIT.ksh.demo.service.ReactionPointService;
import com.KoreaIT.ksh.demo.vo.ResultData;
import com.KoreaIT.ksh.demo.vo.Rq;

@Controller
public class UsrReactionController {

	@Autowired
	private ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	public String doGoodReaciton(String relTypeCode, int relId, String replaceUri) {
		ResultData actorCanMakeReactionRd = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(),
				relTypeCode, relId);
		
		int actorCanMakeReaction = (int) actorCanMakeReactionRd.getData1();
		
		if (actorCanMakeReaction == 1) {
			ResultData rd = reactionPointService.deleteGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return rq.jsReplace("좋아요 취소", replaceUri);
		} else if (actorCanMakeReaction == -1) {
			
			return rq.jsReplace("싫어요 누른 상태입니다.", replaceUri);
		}

		ResultData rd = reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		if (rd.isFail()) {
			rq.jsHitoryBack(rd.getMsg(), "좋아요 실패");
		}

		return rq.jsReplace("좋아요!", replaceUri);
	}


	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	public String doBadReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData actorCanMakeReactionRd = reactionPointService.actorCanMakeReaction(rq.getLoginedMemberId(),
				relTypeCode, relId);
		
		int actorCanMakeReaction = (int) actorCanMakeReactionRd.getData1();
		
		if (actorCanMakeReaction == -1) {
			ResultData rd = reactionPointService.deleteBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return rq.jsReplace("싫어요 취소", replaceUri);
		} else if (actorCanMakeReaction == 1) {
	
			return rq.jsReplace("좋아요 누른 상태입니다.", replaceUri);
		}

		ResultData rd = reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		if (rd.isFail()) {
			rq.jsHitoryBack(rd.getMsg(), "싫어요 실패");
		}

		return rq.jsReplace("싫어요!", replaceUri);
	}
}