package isepddiamniadio.pff.Gestion_performance.dao;

import isepddiamniadio.pff.Gestion_performance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    void save(org.apache.catalina.User user);
}