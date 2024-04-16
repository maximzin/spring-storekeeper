package ru.zinovev.springstorekeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zinovev.springstorekeeper.models.CellAndTools;

import java.util.List;
import java.util.Optional;

@Repository
public interface CellAndToolsRepository extends JpaRepository<CellAndTools, Integer> {
    List<CellAndTools> findByIdCellOrderById(int id);

    void deleteByIdCell(int id);
}
