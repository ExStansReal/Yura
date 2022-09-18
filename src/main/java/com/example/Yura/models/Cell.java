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

    String name;

    Integer size;
}
