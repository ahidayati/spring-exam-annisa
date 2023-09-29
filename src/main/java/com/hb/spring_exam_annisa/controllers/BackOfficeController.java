package com.hb.spring_exam_annisa.controllers;

import com.hb.spring_exam_annisa.exception.WrongFileTypeException;
import com.hb.spring_exam_annisa.models.News;
import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.services.NewsService;
import com.hb.spring_exam_annisa.services.CategoryService;
import com.hb.spring_exam_annisa.services.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(path = "/back-office")
public class BackOfficeController {
    @Autowired
    NewsService newsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StorageService storageService;

    @RequestMapping()
    public ModelAndView backOfficeList(Model model) {
        ModelAndView mv = new ModelAndView("back-office/list");
        List<News> newsList = newsService.findAllNewsByOldestDate();
        List<Category> categories = categoryService.findAll();
        mv.addObject("newsList", newsList);
        mv.addObject("categories", categories);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        News news = new News();
        List<Category> categories = categoryService.findAll();
        ModelAndView mv = new ModelAndView("back-office/form");
        mv.addObject("news", news);
        mv.addObject("categories", categories);
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String formAdd(
            @Valid News news,
            BindingResult bindingResult,
            Model model,
            @RequestParam("uploadImage") MultipartFile imageNews
    ) throws IOException {
        if (news == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
        List<Category> categories = categoryService.findAll();
        news.setPublicationDate(Calendar.getInstance());
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categories);
            return "back-office/form";
        } else {
            try {
                model.addAttribute("categories", categories);
                news.setImage(storageService.store(imageNews));
                newsService.saveNews(news);
                return "redirect:/back-office";
            } catch (WrongFileTypeException e) {
                model.addAttribute("uploadError", "Upload error: Image can not be empty");
                model.addAttribute("categories", categories);
                return "back-office/form";
            }
        }
    }

    @RequestMapping(value = "/edit/{news}", method = RequestMethod.GET)
    public ModelAndView edit(
            @PathVariable(required = false) News news,
            Model model
            ) {
        if(news == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
        ModelAndView mv = new ModelAndView("back-office/form");
        List<Category> categories = categoryService.findAll();
        mv.addObject("news", news);
        mv.addObject("categories", categories);
        return mv;
    }

    @RequestMapping(value = "/edit/{news}", method = RequestMethod.POST)
    public String formEdit(
            @Valid News news,
            BindingResult bindingResult,
            Model model,
            @RequestParam("uploadImage") MultipartFile imageNews
    ) throws IOException {
        if (news == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
        List<Category> categories = categoryService.findAll();
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categories);
            return "back-office/form";
        } else {
            try {
                news.setImage(storageService.store(imageNews));
                newsService.saveNews(news);
                return "redirect:/back-office";
            } catch (WrongFileTypeException e) {
                model.addAttribute("uploadError", "Upload error: Image can not be empty");
                model.addAttribute("categories", categories);
                return "back-office/form";
            }
        }
    }

    @RequestMapping("/delete/{news}")
    public String delete(@PathVariable(required = false) News news) {
        if(news == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        }
        newsService.deleteNews(news);
        newsService.deleteNews(news);
        return "redirect:/back-office";
    }
}
