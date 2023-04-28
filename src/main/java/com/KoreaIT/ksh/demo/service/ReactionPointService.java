package com.KoreaIT.ksh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.ksh.demo.repository.ArticleRepository;
import com.KoreaIT.ksh.demo.repository.ReactionPointRepository;
import com.KoreaIT.ksh.demo.vo.Reaction;
import com.KoreaIT.ksh.demo.vo.ResultData;

@Service
public class ReactionPointService {
	@Autowired
	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public boolean actorCanMakeReaction(int actorId, String relTypeCode, int relId) {
		if (actorId == 0) {
			return false;
		}
		return reactionPointRepository.getSumReactionPointByMemberId(actorId, relTypeCode, relId)==0;
	}

	

}
