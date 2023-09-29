package com.hb.spring_exam_annisa.services;

import com.hb.spring_exam_annisa.models.News;
import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public List<News> findAllNewsByOldestDate() {
        return newsRepository.findAllNewsByOldestDate();
    }

    public List<News> findAllNewsByCategory(Category category) {
        return newsRepository.findAllNewsByCategory(category);
    }

    public void saveNews(News news) {
        newsRepository.save(news);
    }

    public void deleteNews(News news) {
        newsRepository.delete(news);
    }
}
