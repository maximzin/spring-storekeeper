package ru.zinovev.springstorekeeper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "closet")
public class Closet {

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 1, max = 50, message = "Название должно содержать от 1 до 50 символов")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @NotEmpty
    @Column(name = "id_type")
    @Getter
    @Setter
    private int idType;

    @Column(name = "id_ceh")
    @Getter
    @Setter
    private int idCeh;

    public Closet() {}

    public Closet(String name, int idType, int idCeh) {
        this.name = name;
        this.idType = idType;
        this.idCeh = idCeh;
    }
}
