package isepddiamniadio.pff.Gestion_performance.dao;

import isepddiamniadio.pff.Gestion_performance.entities.EvaluationPerformanceAgentProduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationPerformanceAgentProdRepository extends JpaRepository<EvaluationPerformanceAgentProduction, String> {
}