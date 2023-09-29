package com.hb.spring_exam_annisa.controllers;

import com.hb.spring_exam_annisa.models.News;
import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.services.NewsService;
import com.hb.spring_exam_annisa.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/detail/{news}")
    public ModelAndView adsDetail(@PathVariable(required = false) News news) {
        if(news == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
        ModelAndView mv = new ModelAndView("news/detail");

        List<Category> categories = categoryService.findAll();

        mv.addObject("news", news);
        mv.addObject("categories", categories);

        return mv;
    }

    @RequestMapping("/by-category/{category}")
    public ModelAndView adsByCategory(@PathVariable(required = false) Category category) {
        if(category == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }

        ModelAndView mv = new ModelAndView("news/byCategory");

        List<News> newsList = newsService.findAllNewsByCategory(category);
        List<Category> categories = categoryService.findAll();

        mv.addObject("newsList", newsList);
        mv.addObject("category", category);
        mv.addObject("categories", categories);
        return mv;
    }
}
