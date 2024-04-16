package ru.zinovev.springstorekeeper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tool")
public class Tool {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @NotEmpty
    @Size(min = 1, max = 50, message = "Название должно содержать от 1 до 50 символов")
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Size(min = 1, max = 100, message = "Название должно содержать от 1 до 50 символов")
    @Column(name = "manufacturer")
    @Getter
    @Setter
    private String manufacturer;

    public Tool() {}

    public Tool(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

}