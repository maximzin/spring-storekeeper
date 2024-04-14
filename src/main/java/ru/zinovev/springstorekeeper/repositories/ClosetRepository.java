package ru.zinovev.springstorekeeper.repositories;

import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zinovev.springstorekeeper.models.Closet;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClosetRepository extends JpaRepository<Closet, Integer> {

    List<Closet> findByIdCehOrderByName(int id_ceh);

}
