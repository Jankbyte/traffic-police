package ru.jankbyte.trafficpolice.repository;

import ru.jankbyte.trafficpolice.model.entity.Violation;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.Optional;
import java.util.List;

@Repository
public interface ViolationRepository
        extends JpaRepository<Violation, Long> {
    @Transactional
    public void deleteByCarId(Long carId);

    @Transactional
    public void deleteByViolatorId(Long violatorId);

    @Transactional(readOnly = true)
    public List<Violation> findByCarId(@Param("id") Long id);

    @Transactional(readOnly = true)
    public List<Violation> findByViolatorId(@Param("id") Long id);

    @Query("""
        SELECT v FROM Violation v
          LEFT JOIN v.car c
          LEFT JOIN v.violator p
            WHERE (v.active = :active)
              AND (c.id = :id)""")
    @Transactional(readOnly = true)
    public List<Violation> findActiveByCarId(Boolean active,
        @Param("id") Long carId);

    @Query("""
        SELECT v FROM Violation v
          LEFT JOIN v.car c
          LEFT JOIN v.violator p
            WHERE (v.active = :active)
              AND (p.id = :id)""")
    @Transactional(readOnly = true)
    public List<Violation> findActiveByViolatorId(Boolean active,
        @Param("id") Long violatorId);


    @Query("""
        SELECT v FROM Violation v
          LEFT JOIN v.car c
          LEFT JOIN v.violator p""")
    @Transactional(readOnly = true)
    public List<Violation> findAll();
}
