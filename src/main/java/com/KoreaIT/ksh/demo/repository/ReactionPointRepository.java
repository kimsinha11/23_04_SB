package com.KoreaIT.ksh.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {


	@Select("""
			<script>
				SELECT IFNULL(SUM(rp.point),0)
				FROM reactionPoint AS rp
				WHERE rp.relTypeCode = #{relTypeCode}
				AND rp.relId = #{id}
				AND rp.memberId = #{actorId}
			</script>
			""")
	public int getSumReactionPointByMemberId(int actorId, String relTypeCode, int id);

	
	@Insert("""
			<script>
				INSERT INTO reactionPoint
				SET regDate = NOW(),
				updateDate = NOW(),
				relTypeCode = #{relTypeCode},
				relId = #{id},
				memberId = #{actorId},
				`point` = 1
			</script>
			""")
	public int addGoodReactionPoint(int actorId, String relTypeCode, int id);

	
	@Insert("""
			<script>
				INSERT INTO reactionPoint
				SET regDate = NOW(),
				updateDate = NOW(),
				relTypeCode = #{relTypeCode},
				relId = #{id},
				memberId = #{actorId},
				`point` = -1
			</script>
			""")
	public int addBadReactionPoint(int actorId, String relTypeCode, int id);

}
