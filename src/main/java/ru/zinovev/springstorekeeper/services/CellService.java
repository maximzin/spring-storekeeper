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

    @Autowired
    private EntityManager entityManager;

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
    public void deleteCell(int id) {
        cellRepository.deleteById(id);
    }


}
