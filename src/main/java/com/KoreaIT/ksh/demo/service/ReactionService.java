package com.KoreaIT.ksh.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KoreaIT.ksh.demo.repository.ArticleRepository;
import com.KoreaIT.ksh.demo.repository.ReactionRepository;
import com.KoreaIT.ksh.demo.vo.Reaction;
import com.KoreaIT.ksh.demo.vo.ResultData;

@Service
public class ReactionService {
	@Autowired
	private ReactionRepository reactionRepository;

	public ReactionService(ReactionRepository reactionRepository) {
		this.reactionRepository = reactionRepository;
	}

	// 좋아요를 누른 적이 있는지 확인하는 메서드
	public ResultData<?> GoodPointRd(int id, int memberId) {
		int affectedRow = reactionRepository.GoodPointRd(id);

		if (affectedRow == 0) {
			return ResultData.from("F-1", "해당 게시물은 없음", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", "좋아요 증가", "affectedRowRd", affectedRow);
	}

	public int getArticleGoodCount(int id) {
		return reactionRepository.getArticleGoodCount(id);
	}

	// 좋아요 취소
    public ResultData<?> doCancelGoodPoint(int id, String relTypeCode, int memberId) {
        // 해당 게시물에 좋아요를 눌렀는지 확인
        Reaction reaction = reactionRepository.getReaction(id, relTypeCode, memberId);
        if (reaction == null) {
            return ResultData.from("F-1", "해당 게시물에 대한 좋아요 기록이 없습니다.");
        }

        // 좋아요 취소
        int affectedRow = reactionRepository.deleteReaction(id, memberId);
        if (affectedRow == 0) {
            return ResultData.from("F-1", "좋아요 취소에 실패했습니다.");
        }

        return ResultData.from("S-1", "좋아요를 취소했습니다.");
    }

	public ResultData<?> BadPointRd(int id, int memberId) {
		int affectedRow = reactionRepository.BadPointRd(id);

		if (affectedRow == 0) {
			return ResultData.from("F-1", "해당 게시물은 없음", "affectedRow", affectedRow);
		}

		return ResultData.from("S-1", "싫어요 증가", "affectedRowRd", affectedRow);
	}

	public int getArticleBadCount(int id) {
		return reactionRepository.getArticleBadCount(id);
	}

}
