package com.hb.spring_exam_annisa.services;

import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
