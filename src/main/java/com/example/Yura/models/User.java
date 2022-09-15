package com.example.Yura.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_User;

    private String username;
    private String password;
    private String Name;
    private String Surname;
    private String Patronymec;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User(String username, String password, String name, String surname, String patronymec, Post post, Set<Role> roles) {
        this.username = username;
        this.password = password;
        Name = name;
        Surname = surname;
        Patronymec = patronymec;
        this.post = post;
        this.roles = roles;
    }
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = @JoinColumn(name = "User_Id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    public User() {

    }

    public Long getID_User() {
        return ID_User;
    }

    public void setID_User(Long ID_User) {
        this.ID_User = ID_User;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPatronymec() {
        return Patronymec;
    }

    public void setPatronymec(String patronymec) {
        Patronymec = patronymec;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }






}
