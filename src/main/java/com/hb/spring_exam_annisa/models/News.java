package com.hb.spring_exam_annisa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;


@Entity
@Table(name = "news")
public class News {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false, name = "id")
    private long id;

    @Basic
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 50, message = "Title description length is 50 characters maximum")
    @Column(name = "title")
    public String title;

    @Basic
    /*@NotBlank(message = "Image cannot be empty")*/
    @Column(name = "image")
    private String image;

    @Basic
    @NotBlank(message = "Content cannot be empty")
    @Column(name = "content")
    private String content;

    @Basic
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "publication_date")
    /*@NotNull(message = "publication_date cannot be empty")*/
    private Calendar publicationDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    public News() {
    }

    public News(long id, String title, String image, String content, @NotNull(message = "publication_date cannot be empty") Calendar publicationDate, Category category) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.publicationDate = publicationDate;
        this.category = category;
    }

    public News(String title, String image, String content, @NotNull(message = "publication_date cannot be empty") Calendar publicationDate, Category category) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.publicationDate = publicationDate;
        this.category = category;
    }

    public News(String title, String image, String content, @NotNull(message = "publication_date cannot be empty") Calendar publicationDate) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.publicationDate = publicationDate;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
