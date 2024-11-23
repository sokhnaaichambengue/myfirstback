package isepddiamniadio.pff.Gestion_performance.service;


import isepddiamniadio.pff.Gestion_performance.dao.EvaluationnPerformanceAiRepository;
import isepddiamniadio.pff.Gestion_performance.dao.EvaluationprodRepository;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationnPerformanceAi;
import isepddiamniadio.pff.Gestion_performance.entities.Evaluationprod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationAiService {

    @Autowired
    private EvaluationnPerformanceAiRepository evaluationnPerformanceAiRepository;

    // Create or Update
    public EvaluationnPerformanceAi saveEvaluationAi(EvaluationnPerformanceAi evaluationnPerformanceAi) {
        return evaluationnPerformanceAiRepository.save(evaluationnPerformanceAi);
    }
    public EvaluationnPerformanceAi updateEvaluationAi(EvaluationnPerformanceAi evaluationnPerformanceAi) {
        return evaluationnPerformanceAiRepository.save( evaluationnPerformanceAi);
    }

    // Read
    public List<EvaluationnPerformanceAi> getAllEvaluationAi() {
        return evaluationnPerformanceAiRepository.findAll();
    }

    public Optional<EvaluationnPerformanceAi> getEvaluationAiById(String indicateurPerformance) {
        return evaluationnPerformanceAiRepository.findById(indicateurPerformance);
    }

    // Delete
    public void deleteEvaluationAi(String indicateurPerformance) {
        evaluationnPerformanceAiRepository.deleteById(indicateurPerformance);
    }
}




