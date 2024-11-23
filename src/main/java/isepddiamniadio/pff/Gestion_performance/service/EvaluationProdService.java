package isepddiamniadio.pff.Gestion_performance.service;

import isepddiamniadio.pff.Gestion_performance.dao.EvaluationCommRepository;
import isepddiamniadio.pff.Gestion_performance.dao.EvaluationprodRepository;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationComm;
import isepddiamniadio.pff.Gestion_performance.entities.Evaluationprod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationProdService {

    @Autowired
    private EvaluationprodRepository evaluationprodRepository;

    // Create or Update
    public Evaluationprod saveEvaluationprod(Evaluationprod evaluationprod) {
        return evaluationprodRepository.save(evaluationprod);
    }
    public Evaluationprod updateEvaluationprod(Evaluationprod evaluationprod) {
        return evaluationprodRepository.save( evaluationprod);
    }

    // Read
    public List<Evaluationprod> getAllEvaluatioprod() {
        return evaluationprodRepository.findAll();
    }

    public Optional<Evaluationprod> getEvaluationProdById(String indicateurPerformance) {
        return evaluationprodRepository.findById(indicateurPerformance);
    }

    // Delete
    public void deleteEvaluationprod(String indicateurPerformance) {
        evaluationprodRepository.deleteById(indicateurPerformance);
    }
}




