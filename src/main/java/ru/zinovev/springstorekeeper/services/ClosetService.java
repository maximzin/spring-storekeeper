package ru.zinovev.springstorekeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zinovev.springstorekeeper.models.Closet;
import ru.zinovev.springstorekeeper.models.Tool;
import ru.zinovev.springstorekeeper.repositories.ClosetRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClosetService {

    private final ClosetRepository closetRepository;

    @Autowired
    public ClosetService(ClosetRepository closetRepository) {
        this.closetRepository = closetRepository;
    }

    public List<Closet> findByIdCeh(int id_ceh) {
        return closetRepository.findByIdCehOrderByName(id_ceh);
    }

    public List<Closet> findAll() {
        return closetRepository.findAll();
    }

    public Closet findOne(int id) {
        Optional<Closet> foundCloset = closetRepository.findById(id);
        return foundCloset.orElse(null);
    }

    @Transactional
    public void saveCloset(Closet closet) {
        closetRepository.save(closet);
    }

    @Transactional
    public void updateCloset(int id, Closet updatedCloset) {
        updatedCloset.setId(id);
        closetRepository.save(updatedCloset);
    }

    @Transactional
    public void deleteCloset(int id) {
        closetRepository.deleteById(id);
    }
    
}
