package isepddiamniadio.pff.Gestion_performance.dao;

import isepddiamniadio.pff.Gestion_performance.entities.HierarchieProduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HierarchieProductionRepository extends JpaRepository<HierarchieProduction, Integer> {
  }