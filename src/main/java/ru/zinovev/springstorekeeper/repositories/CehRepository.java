package ru.zinovev.springstorekeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zinovev.springstorekeeper.models.Ceh;

@Repository
public interface CehRepository extends JpaRepository<Ceh, Integer> {
}
