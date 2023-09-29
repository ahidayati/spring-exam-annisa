package com.hb.spring_exam_annisa.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false, name = "id")
    private long id;

    @Basic
    @Column(nullable = false, name = "category_name")
    public String categoryName;

    @OneToMany(mappedBy = "category")
    private List<News> advertisementList;


    public Category() {
    }

    public Category(long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<News> getAdvertisementList() {
        return advertisementList;
    }

    public void addProduct (News advertisement) {
        this.advertisementList.add(advertisement);
    }

    public void removeProduct (News advertisement) {
        this.advertisementList.remove(advertisement);
    }
}
