package com.example.repository;

import com.example.entity.ArticleEntity;
import com.example.enums.ArticleStatus;
import com.example.enums.GeneralStatus;
import com.example.mapper.ArticleShortInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {

    @Transactional
    @Modifying
    @Query("update  ArticleEntity  set visible = false where id =:id")
    int deleteArticle(@Param("id") String articleId);

    @Transactional
    @Modifying
    @Query("update  ArticleEntity  set status = :status where id =:id")
    int changeStatus(@Param("status") ArticleStatus status, @Param("id") String id);


    List<ArticleEntity> findTop5ByTypeIdAndStatusAndVisibleOrderByCreatedDateDesc(Integer typeId,
                                                                                  ArticleStatus status,
                                                                                  Boolean visible);

    @Query("SELECT new ArticleEntity(id,title,description,attachId,publishedDate) From ArticleEntity where status =:status and visible = true and typeId =:typeId order by createdDate desc limit 5")
    List<ArticleEntity> find5ByTypeId(@Param("typeId") Integer typeId, @Param("status") ArticleStatus status);


    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id,a.published_date " +
            " FROM article AS a  where  a.type_id =:typeId and status =:status order by created_date desc Limit :limit",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find5ByTypeIdNative(@Param("typeId") Integer typeId,
                                                     @Param("status") String status,
                                                     @Param("limit") Integer limit);
}
