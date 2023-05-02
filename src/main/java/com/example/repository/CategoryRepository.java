package com.example.repository;

import com.example.entity.CategoryEntity;
import com.example.mapper.ICategoryMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>, PagingAndSortingRepository<CategoryEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update CategoryEntity  set visible = false , prtId =:prtId where id =:id")
    int updateVisible(@Param("id") Integer id, @Param("prtId") Integer prtId);


    @Query(value = "SELECT a.id as id, a.name_uz as name, a.key as aKey from region a", nativeQuery = true)
    List<ICategoryMapper> getByLangUz();

    @Query(value = "SELECT a.id as id, a.name_ru as name, a.key as key from region a", nativeQuery = true)
    List<ICategoryMapper> getByLangRu();

    @Query(value = "SELECT a.id as id, a.name_en as name, a.key as key from region a", nativeQuery = true)
    List<ICategoryMapper> getByLangEn();
}
