package com.example.Yura.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Post;

    private String Name;

    private Integer Salary;

    @OneToMany (mappedBy = "Post", fetch = FetchType.EAGER)
    private Collection<Post> post;

    public Post(String name, Integer salary, Collection<Post> post) {
        Name = name;
        Salary = salary;
        this.post = post;
    }

    public Post() {

    }

    public Long getID_Post() {
        return ID_Post;
    }

    public void setID_Post(Long ID_Post) {
        this.ID_Post = ID_Post;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }

    public Collection<Post> getPost() {
        return post;
    }

    public void setPost(Collection<Post> post) {
        this.post = post;
    }
}
