package ru.zinovev.springstorekeeper.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zinovev.springstorekeeper.models.ToolType;
import ru.zinovev.springstorekeeper.repositories.ToolTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ToolTypeService {

    private final ToolTypeRepository toolTypeRepository;

    @Autowired
    public ToolTypeService(ToolTypeRepository toolTypeRepository) {
        this.toolTypeRepository = toolTypeRepository;
    }

    public List<ToolType> findAll() {
        return toolTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public ToolType findOne(int id) {
        Optional<ToolType> foundToolType = toolTypeRepository.findById(id);
        return foundToolType.orElse(null);
    }

    @Transactional
    public void saveToolType(ToolType ceh) {toolTypeRepository.save(ceh);
    }

    @Transactional
    public void updateToolType(int id, ToolType updatedToolType) {
        updatedToolType.setId(id);
        toolTypeRepository.save(updatedToolType);
    }

    @Transactional
    public void deleteToolType(int id) {
        toolTypeRepository.deleteById(id);
    }

}
