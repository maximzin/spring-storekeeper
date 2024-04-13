package ru.zinovev.springstorekeeper.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cells_and_tools")
public class CellAndTool {

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

    public CellAndTool() {}

    public CellAndTool(int idCell, int idTool) {
        this.idCell = idCell;
        this.idTool = idTool;
    }
}
