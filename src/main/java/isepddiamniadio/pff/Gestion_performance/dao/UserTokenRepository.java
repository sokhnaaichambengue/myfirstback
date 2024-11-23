package isepddiamniadio.pff.Gestion_performance.dao;

import isepddiamniadio.pff.Gestion_performance.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {
}