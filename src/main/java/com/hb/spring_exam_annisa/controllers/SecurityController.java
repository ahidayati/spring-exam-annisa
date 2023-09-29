package com.hb.spring_exam_annisa.controllers;

import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class SecurityController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("security/login");
        return mv;
    }

    @RequestMapping(value = "/errors/access-denied", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView mv = new ModelAndView("errors/access-denied");
        List<Category> categories = categoryService.findAll();
        mv.addObject("categories", categories);
        return mv;
    }
}
