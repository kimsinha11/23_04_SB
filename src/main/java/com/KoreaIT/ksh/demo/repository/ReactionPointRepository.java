package com.KoreaIT.ksh.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KoreaIT.ksh.demo.vo.Reaction;

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

}
