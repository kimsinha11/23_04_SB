package com.KoreaIT.ksh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.ksh.demo.repository.ArticleRepository;
import com.KoreaIT.ksh.demo.repository.ReactionRepository;
import com.KoreaIT.ksh.demo.vo.ResultData;

@Service
public class ReactionService {
	@Autowired
	private ArticleRepository articleRepository;
	private ReactionRepository reactionRepository;

	public ReactionService(ReactionRepository reactionRepository) {
		this.reactionRepository = reactionRepository;
	}



	public ResultData doIncreaseGoodCount(int id) {
		int affectedRow = reactionRepository.increaseGoodCount(id);

		if (affectedRow == 0) {
			return ResultData.from("F-1", "해당 게시물은 없음", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", "좋아요 증가", "affectedRowRd", affectedRow);
	}



	public int getReactionGoodCount(int id) {
		return reactionRepository.getReactionGoodCount(id);
	}

}
