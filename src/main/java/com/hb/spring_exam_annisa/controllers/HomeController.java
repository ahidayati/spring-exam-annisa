package com.hb.spring_exam_annisa.controllers;

import com.hb.spring_exam_annisa.models.News;
import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.services.NewsService;
import com.hb.spring_exam_annisa.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping()
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");

        List<News> newsList = newsService.findAllNewsByOldestDate();
        List<Category> categories = categoryService.findAll();
        mv.addObject("newsList", newsList);
        mv.addObject("categories", categories);
        return mv;
    }
}
