package com.KoreaIT.ksh.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.ksh.demo.vo.Reaction;

@Mapper
public interface ReactionRepository {

	@Insert("""
			    <script>
			    INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relTypeCode = 'article',
			relId = #{id},
			`point` = 1
			ON DUPLICATE KEY UPDATE `point` = IF(`point` = 1, `point`, 1);
			    </script>
			    """)
	public int GoodPointRd(int id);

	@Select("""
				<script>
				SELECT SUM(IF(`point` &gt; 0, `point`, 0))
			FROM reactionPoint
			WHERE relId = #{id}
				</script>
				""")
	public int getArticleGoodCount(int id);

	@Insert("""
				<script>
				INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relTypeCode = 'article',
			relId = #{id},
			`point` = -1;
				</script>
				""")
	public int BadPointRd(int id);

	@Select("""
				<script>
				SELECT SUM(IF(`point` &lt; 0, `point`, 0))
			FROM reactionPoint
			WHERE relId = #{id}
				</script>
				""")
	public int getArticleBadCount(int id);

	@Delete("""
			DELETE FROM reactionPoint
			WHERE relTypeCode = 'article' AND relId = #{relId} AND memberId = #{memberId}
			""")

	public Reaction getReaction(int relId, String relTypeCode, int memberId);

	@Delete("""
		    DELETE FROM reactionPoint
		    WHERE relId = #{id} AND memberId = #{memberId}
		""")
	public int deleteReaction(int id, int memberId);

}
