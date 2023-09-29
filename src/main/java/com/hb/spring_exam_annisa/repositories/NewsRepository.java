package com.hb.spring_exam_annisa.repositories;

import com.hb.spring_exam_annisa.models.News;
import com.hb.spring_exam_annisa.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
    News findByTitle(String title);

    @Query("SELECT n FROM News n ORDER BY n.publicationDate DESC")
    List<News> findAllNewsByOldestDate();

    @Query("SELECT n FROM News n WHERE n.category = :category ORDER BY n.publicationDate DESC")
    List<News> findAllNewsByCategory(@Param("category")Category category);

    @Override
    void delete(News news);
}
