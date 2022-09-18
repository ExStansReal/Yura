package com.example.Yura.models;



import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Zoopark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_zoopark;

    String name,adress;

    @OneToMany (mappedBy = "zoopark", fetch = FetchType.EAGER)
    private Collection<Cell> cells;


    public Zoopark(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    public Zoopark() {

    }

    public Long getId_zoopark() {
        return id_zoopark;
    }

    public void setId_zoopark(Long id_zoopark) {
        this.id_zoopark = id_zoopark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Collection<Cell> getCells() {
        return cells;
    }

    public void setCells(Collection<Cell> cells) {
        this.cells = cells;
    }
}
