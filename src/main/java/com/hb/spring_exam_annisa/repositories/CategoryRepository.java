package com.hb.spring_exam_annisa.repositories;

import com.hb.spring_exam_annisa.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    public Category findByCategoryName(String categoryName);

    @Override
    List<Category> findAll();
}
