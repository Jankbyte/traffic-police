package ru.jankbyte.trafficpolice.repository;

import ru.jankbyte.trafficpolice.model.entity.Car;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface CarRepository
        extends JpaRepository<Car, Long> {
    @Query("""
      SELECT c FROM Car c
        LEFT JOIN FETCH c.owner o
          WHERE o.id = :id""")
    @Transactional(readOnly = true)
    public List<Car> findByOwnerId(Long id);

    @Query("""
      SELECT c FROM Car c
        LEFT JOIN FETCH c.owner o""")
    @Transactional(readOnly = true)
    public List<Car> findAll();
}
