package ru.jankbyte.trafficpolice.repository;

import ru.jankbyte.trafficpolice.model.entity.auth.User;
import ru.jankbyte.trafficpolice.model.entity.auth.Roles;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    @Query("""
      SELECT u FROM User u
        LEFT JOIN FETCH u.role r""")
    @Transactional(readOnly = true)
    public List<User> findAll();

    @Query("""
      SELECT u FROM User u
        LEFT JOIN FETCH u.role r
          WHERE u.name = :name""")
    @Transactional(readOnly = true)
    public Optional<User> findByName(String name);

    @Transactional
    public void deleteByName(String name);
}
