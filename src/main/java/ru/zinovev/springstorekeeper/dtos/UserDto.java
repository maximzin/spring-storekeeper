package ru.zinovev.springstorekeeper.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.zinovev.springstorekeeper.models.Role;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
}