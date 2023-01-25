package ru.jankbyte.trafficpolice.repository;

import ru.jankbyte.trafficpolice.model.entity.Person;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long> {
    @Query("""
      SELECT p FROM Person p
        LEFT JOIN FETCH p.address a
        LEFT JOIN FETCH p.contact c""")
    @Transactional(readOnly = true)
    public List<Person> findAll();

    @Query("""
      SELECT p FROM Person p
        LEFT JOIN FETCH p.address a
        LEFT JOIN FETCH p.contact c
          WHERE c.email = :email""")
    @Transactional(readOnly = true)
    public Optional<Person> findByEmail(String email);

    @Query("""
      SELECT p FROM Person p
        LEFT JOIN FETCH p.address a
        LEFT JOIN FETCH p.contact c
          WHERE c.phoneNumber = :phoneNumber""")
    @Transactional(readOnly = true)
    public Optional<Person> findByPhoneNumber(String phoneNumber);
}
