package com.example.Yura.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cell;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private CellType cellType;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Zoopark zoopark;

    private String name;

    private Integer size;

    public String getNameZoopark()
    {
        return zoopark.getName();
    }

    public String getTypeName()
    {
        return cellType.getName();
    }

    public Cell(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public Cell() {

    }

    public Long getId_cell() {
        return id_cell;
    }

    public void setId_cell(Long id_cell) {
        this.id_cell = id_cell;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public Zoopark getZoopark() {
        return zoopark;
    }

    public void setZoopark(Zoopark zoopark) {
        this.zoopark = zoopark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
