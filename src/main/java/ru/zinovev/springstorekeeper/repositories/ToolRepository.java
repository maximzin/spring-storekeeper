package ru.zinovev.springstorekeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zinovev.springstorekeeper.models.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
}
