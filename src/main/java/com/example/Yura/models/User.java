package com.example.Yura.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;


    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Поле не может быть меньше или больше", min = 1, max = 100)
    private String username,name,surname,patronymec;

    private String password;

    @OneToOne(optional = true, mappedBy = "user")
    private Kart kart;


    private boolean active;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Post post;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "User_Id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(String username, String password, String name, String surname, String patronymec) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymec = patronymec;
    }

    public User(String username, String password, String name, Kart kart, String surname, String patronymec, boolean active, Post post, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.kart = kart;
        this.surname = surname;
        this.patronymec = patronymec;
        this.active = active;
        this.post = post;
        this.roles = roles;
    }

    public String getPostName() {
        return post.getName();
    }
    public User() {

    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kart getKart() {
        return kart;
    }

    public void setKart(Kart kart) {
        this.kart = kart;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymec() {
        return patronymec;
    }

    public void setPatronymec(String patronymec) {
        this.patronymec = patronymec;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
