package com.example.Yura.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class CellType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_celltype;

    private String name,climat;

    private Double temperature;

    @OneToMany (mappedBy = "cellType", fetch = FetchType.EAGER)
    private Collection<Cell> cells;

    public CellType(String name, String climat, Double temperature) {
        this.name = name;
        this.climat = climat;
        this.temperature = temperature;
    }

    public CellType() {

    }

    public Long getId_celltype() {
        return id_celltype;
    }

    public void setId_celltype(Long id_celltype) {
        this.id_celltype = id_celltype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimat() {
        return climat;
    }

    public void setClimat(String climat) {
        this.climat = climat;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Collection<Cell> getCells() {
        return cells;
    }

    public void setCells(Collection<Cell> cells) {
        this.cells = cells;
    }
}
