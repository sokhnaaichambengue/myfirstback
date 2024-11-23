package isepddiamniadio.pff.Gestion_performance.service;


import isepddiamniadio.pff.Gestion_performance.dao.EvaluationGcfRepository;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationGcf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationGcfService {

    @Autowired
    private EvaluationGcfRepository evaluationGcfRepository;

    // Create or Update
    public EvaluationGcf saveEvaluationGcf(EvaluationGcf evaluationGcf) {
        return evaluationGcfRepository.save(evaluationGcf);
    }

    public EvaluationGcf updateEvaluationGcf(EvaluationGcf evaluationGcf) {
        return evaluationGcfRepository.save(evaluationGcf);
    }

    // Read
    public List<EvaluationGcf> getAllEvaluationGcf() {
        return evaluationGcfRepository.findAll();
    }

    public Optional<EvaluationGcf> getEvaluationGcfById(String indicateurPerformance) {
        return evaluationGcfRepository.findById(indicateurPerformance);
    }

    // Delete
    public void deleteEvaluationGcf(String indicateurPerformance) {
        evaluationGcfRepository.deleteById(indicateurPerformance);
    }
}
