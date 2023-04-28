package com.KoreaIT.ksh.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.ksh.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public void writeArticle(String title, String body, int memberId, int boardId);

	public List<Article> getArticles(int boardId, int i, int itemsPerPage, String searchKeyword, Integer searchId);

	public Article getArticle(int id);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

	public int getLastInsertId();

	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM article AS A
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			<if test="searchKeyword != null and searchKeyword != ''">
			<choose>
				<when test="searchId != null and searchId.intValue() == 1">
					AND title LIKE CONCAT('%', #{searchKeyword}, '%')
				</when>
				<when test="searchId != null and searchId.intValue() == 2">
					AND body LIKE CONCAT('%', #{searchKeyword}, '%')
				</when>
				<otherwise>
					AND (title LIKE CONCAT('%', #{searchKeyword}, '%') OR body LIKE
					CONCAT('%', #{searchKeyword}, '%'))
				</otherwise>
			</choose>
		</if>
	
			</script>
				""")
	public int getArticlesCount(int boardId, Integer searchId, String searchKeyword);


	@Select("""
			<script>
				SELECT hitCount
				FROM article
				WHERE id = #{id}
			</script>
			""")
	public int getArticleHitCount(int id);

	
	public int increaseHitCount(int id);

	public Object findById(int id);

	@Select("""
			<script>
				SELECT IFNULL(SUM(rp.point),0)
				FROM reactionPoint AS rp
				WHERE rp.relTypeCode = 'article'
				AND rp.relId = #{id}
				AND rp.memberId = #{actorId}
			</script>
			""")
	public int getSumReactionPointByMemberId(int actorId, int id);

	
}