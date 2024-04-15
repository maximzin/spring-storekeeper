package ru.zinovev.springstorekeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zinovev.springstorekeeper.models.ClosetType;

import java.util.Optional;

@Repository
public interface ClosetTypeRepository extends JpaRepository<ClosetType, Integer> {

}
