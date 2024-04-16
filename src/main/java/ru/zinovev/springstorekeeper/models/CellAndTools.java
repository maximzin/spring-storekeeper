package ru.zinovev.springstorekeeper.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cells_and_tools")
public class CellAndTools {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "id_cell")
    @Getter
    @Setter
    private int idCell;

    @Column(name = "id_tool")
    @Getter
    @Setter
    private int idTool;

    @Column(name = "count")
    @Getter
    @Setter
    private int count;
    КЛЮЧИ НАДО

    public CellAndTools() {}

    public CellAndTools(int idCell, int idTool, int count) {
        this.idCell = idCell;
        this.idTool = idTool;
        this.count = count;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tool", referencedColumnName = "id", insertable=false, updatable=false)
    @Getter
    @Setter
    private Tool tool;
}
