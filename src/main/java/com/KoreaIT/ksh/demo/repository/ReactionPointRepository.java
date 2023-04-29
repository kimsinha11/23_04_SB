package com.KoreaIT.ksh.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {


	public int getSumReactionPointByMemberId(int actorId, String relTypeCode, int relId);

	public int addGoodReactionPoint(int actorId, String relTypeCode, int relId);
	
	public int addBadReactionPoint(int actorId, String relTypeCode, int relId);

	public void deleteReactionPoint(int actorId, String relTypeCode, int relId);

}
