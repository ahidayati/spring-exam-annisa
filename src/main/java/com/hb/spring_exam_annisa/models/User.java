package com.hb.spring_exam_annisa.models;


import com.hb.spring_exam_annisa.validationGroups.RegisterGroup;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false, name = "id")
    private long id;

    @Basic
    @Column(name = "email")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "please enter a valid email address")
    private String email;

    @Basic
    @Column(nullable = false, name = "firstname")
    public String firstname;

    @Basic
    @Column(nullable = false, name = "lastname")
    public String lastname;


    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must contain at least 8 characters, a number, and a letter",
            groups = RegisterGroup.class)
    @Column(nullable = false, name ="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne
    @Valid
    private Category category;

    public User() {
        this.roles = new ArrayList<Role>();
    }

    public User(String email, String firstname, String lastname, String password) {
        this.roles = new ArrayList<Role>();
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public User(long id, String email, String firstname, String lastname, String password) {
        this.id = id;
        this.roles = new ArrayList<Role>();
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
