package isepddiamniadio.pff.Gestion_performance.service;
import isepddiamniadio.pff.Gestion_performance.dao.EvaluationGrhRepository;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationGrh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationGrhService {

    @Autowired
    private EvaluationGrhRepository evaluationGrhRepository;

    // Create or Update
    public EvaluationGrh saveEvaluationGrh(EvaluationGrh evaluationGrh) {
        return evaluationGrhRepository.save(evaluationGrh);
    }

    public EvaluationGrh updateEvaluationGrh(EvaluationGrh evaluationGrh) {
        return evaluationGrhRepository.save(evaluationGrh);
    }

    // Read
    public List<EvaluationGrh> getAllEvaluationGrh() {
        return evaluationGrhRepository.findAll();
    }

    public Optional<EvaluationGrh> getEvaluationGrhById(String indicateurPerformance) {
        return evaluationGrhRepository.findById(indicateurPerformance);
    }

    // Delete
    public void deleteEvaluationGrh(String indicateurPerformance) {
        evaluationGrhRepository.deleteById(indicateurPerformance);
    }
}
