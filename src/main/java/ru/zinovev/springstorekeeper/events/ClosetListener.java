package ru.zinovev.springstorekeeper.events;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zinovev.springstorekeeper.models.Cell;
import ru.zinovev.springstorekeeper.models.Closet;
import ru.zinovev.springstorekeeper.services.CellService;

@Component
public class ClosetListener {

    @PersistenceContext
    private EntityManager entityManager;

    @PostPersist
    public void postPersist(Closet closet) {
        int cellCount = 30;

        for (int i = 1; i <= cellCount; i++) {
            Cell cell = new Cell(closet.getId(), i);
            entityManager.persist(cell);
        }
    }

}
