package com.example.Yura.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Kart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_kart;


    @NotEmpty(message = "Число не может быть пустым")
    private String personalkey;

    private Boolean allowed;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @NotEmpty(message = "Название не может быть пустым!")
    @Size(message = "Строка не может быть меньше или больше", min = 3, max = 1000)
    private String type;

    public Kart(String  personal_Key, Boolean allowedD, User user, String typeD) {
        personalkey = personal_Key;
        allowed = allowedD;
        this.user = user;
        type = typeD;
    }

    public String getUserUserName()
    {
        return user.getUsername();
    }

    public Kart() {

    }

    public Long getID_Kart() {
        return id_kart;
    }

    public void setID_Kart(Long ID_Kart) {
        this.id_kart = ID_Kart;
    }

    public String getPersonal_Key() {
        return personalkey;
    }

    public void setPersonal_Key(String personal_Key) {
        personalkey = personal_Key;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowedD) {
        allowed = allowedD;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String typeD) {
        type = typeD;
    }
}
