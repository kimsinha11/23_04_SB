package com.KoreaIT.ksh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.ksh.demo.repository.ReactionPointRepository;
import com.KoreaIT.ksh.demo.vo.ResultData;

@Service
public class ReactionPointService {
	@Autowired
	private ReactionPointRepository reactionPointRepository;
	@Autowired
	private ArticleService articleService;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public ResultData actorCanMakeReaction(int actorId, String relTypeCode, int relId) {
		if (actorId == 0) {
			return ResultData.from("F-L", "로그인 후 이용해주세요.");
		}
		int sumReactionPointByMemberId = reactionPointRepository.getSumReactionPointByMemberId(actorId, relTypeCode, relId);
		
		if(sumReactionPointByMemberId != 0) {
			return ResultData.from("F-1", "추천 불가", "sumReactionPointByMemberId", sumReactionPointByMemberId);
		}

		return ResultData.from("S-1", "추천 가능", "sumReactionPointByMemberId", sumReactionPointByMemberId);

	}

	public ResultData addGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		int affectedRow = reactionPointRepository.addGoodReactionPoint(actorId, relTypeCode, relId);
		if(affectedRow != 1) {
			return ResultData.from("F-11", "좋아요 실패");
		}
		switch (relTypeCode) {
		case "article": 
			articleService.increaseGoodReactionPoint(relId);
			break;
		}
		
		return ResultData.from("S-1", "좋아요 처리 완료");
	}

	public ResultData addBadReactionPoint(int actorId, String relTypeCode, int relId) {
		int affectedRow = reactionPointRepository.addBadReactionPoint(actorId, relTypeCode, relId);
		if(affectedRow != 1) {
			return ResultData.from("F-11", "싫어요 실패");
		}
		switch (relTypeCode) {
		case "article": 
			articleService.increaseBadReactionPoint(relId);
			break;
		}
		
		return ResultData.from("S-1", "싫어요 처리 완료");
	}
	
	
	public ResultData deleteGoodReactionPoint(int actorId, String relTypeCode, int relId) {
		reactionPointRepository.deleteGoodReactionPoint(actorId, relTypeCode, relId);
		
		switch (relTypeCode) {
		case "article":
			articleService.decreaseGoodReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "좋아요 취소 됨");
	}

	public ResultData deleteBadReactionPoint(int actorId, String relTypeCode, int relId) {
	reactionPointRepository.deleteBadReactionPoint(actorId, relTypeCode, relId);
		
		switch (relTypeCode) {
		case "article":
			articleService.decreaseBadReactionPoint(relId);
			break;
		}
		return ResultData.from("S-1", "좋아요 취소 됨");
	}
	
}

