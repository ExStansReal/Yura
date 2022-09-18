package com.example.Yura.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;

    @NotEmpty(message = "Название не может быть пустым!")
    @Size(message = "Строка не может быть меньше или больше", min = 3, max = 1000)
    private String name;
    @Min(message = "Число не может быть отрицательным",value = 0)
    @Max(message = "Число не может быть больше 1000000",value = 1000000)
    @NotNull(message = "Число не может быть пустым")
    private Integer salary;

    @OneToMany (mappedBy = "post", fetch = FetchType.EAGER)
    private Collection<User> users;

    public Post(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }

    public Post(String name, Integer salary, Collection<User> users) {
        this.name = name;
        this.salary = salary;
        this.users = users;
    }

    public Post() {

    }

    public Long getId_ppst() {
        return id_post;
    }

    public void setId_ppst(Long id_ppst) {
        this.id_post = id_ppst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
