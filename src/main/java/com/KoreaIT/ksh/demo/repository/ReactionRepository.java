package com.KoreaIT.ksh.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReactionRepository {

	@Update("""
				<script>
			UPDATE reactionPoint
			INNER JOIN article
			SET `point` = 1
			WHERE article.id = #{id}
				</script>
					""")
	public int increaseGoodCount(int id);

	@Select("""
			<script>
			SELECT article.id,`point`
			FROM reactionPoint
			INNER JOIN article
			ON reactionPoint.relId = article.id
			WHERE article.id = #{id}
			GROUP BY article.id
			</script>
			""")
	public int getReactionGoodCount(int id);

}
