package isepddiamniadio.pff.Gestion_performance.service;
import isepddiamniadio.pff.Gestion_performance.dao.EvaluationCommRepository;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationComm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationCommService {

    @Autowired
    private EvaluationCommRepository evaluationCommRepository;

    // Create or Update
    public EvaluationComm saveevationcommercialisation(EvaluationComm evaluationComm) {
        return evaluationCommRepository.save(evaluationComm);
    }
    public EvaluationComm updateEvaluationCommercialisation(EvaluationComm evaluationComm) {
        return evaluationCommRepository.save(evaluationComm);
    }

    // Read
    public List<EvaluationComm> getAllEvaluationscommercialisation() {
        return evaluationCommRepository.findAll();
    }

    public Optional<EvaluationComm> getEvaluationcommercialisationById(String indicateurPerformance) {
        return evaluationCommRepository.findById(indicateurPerformance);
    }

    // Delete
    public void deleteEvaluationCommercialisation(String indicateurPerformance) {
        evaluationCommRepository.deleteById(indicateurPerformance);
    }
}

