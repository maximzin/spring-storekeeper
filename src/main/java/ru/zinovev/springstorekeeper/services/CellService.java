package ru.zinovev.springstorekeeper.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zinovev.springstorekeeper.models.Cell;
import ru.zinovev.springstorekeeper.repositories.CellRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CellService {

    private final CellRepository cellRepository;

    @Autowired
    public CellService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public List<Cell> findByIdCloset(int id_closet) {
        return cellRepository.findByIdClosetOrderByNumber(id_closet);
    }

    public List<Cell> findAll() {
        return cellRepository.findAll();
    }

    public Cell findOne(int id) {
        Optional<Cell> foundCell = cellRepository.findById(id);
        return foundCell.orElse(null);
    }

    @Transactional
    public void saveCell(Cell cell) {
        cellRepository.save(cell);
    }

    @Transactional
    public void updateCell(int id, Cell updatedCell) {
        updatedCell.setId(id);
        cellRepository.save(updatedCell);
    }



    @Transactional
    public void createCells(int id_closet, int count) {
        for (int i = 1; i <= count; i++) {
            Cell cell = new Cell(id_closet, i);
            // Настройте связанные сущности, если необходимо
            cellRepository.save(cell);
        }
    }

    @Transactional
    public void deleteCells(int id_closet) {
        cellRepository.deleteByIdCloset(id_closet);
    }

}
