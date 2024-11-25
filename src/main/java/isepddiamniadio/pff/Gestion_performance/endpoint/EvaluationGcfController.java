package isepddiamniadio.pff.Gestion_performance.endpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationGcf;
import isepddiamniadio.pff.Gestion_performance.service.EvaluationGcfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "Evaluations de Commercialisation") // Tag pour regrouper les opérations sous "Evaluations de Commercialisation"
@RestController
@RequestMapping("/api/evaluationgcf")
public class EvaluationGcfController {

    @Autowired
    private EvaluationGcfService evaluationGcfService;

    // Ajouter une évaluation
    @ApiOperation(value = "Ajouter une évaluation de performance", notes = "Cette méthode permet d'ajouter une évaluation de performance pour un agent.")
    @PostMapping
    public EvaluationGcf addEvaluation(@RequestBody EvaluationGcf evaluationGcf) {
        return evaluationGcfService.saveEvaluationGcf(evaluationGcf);
    }

    // Mettre à jour une évaluation
    @ApiOperation(value = "Mettre à jour une évaluation de performance", notes = "Cette méthode permet de mettre à jour les informations d'une évaluation de performance.")
    @PutMapping("/{indicateurPerformance}") // Utilisation de l'indicateurPerformance comme identifiant
    public EvaluationGcf updateEvaluation(@PathVariable String indicateurPerformance, @RequestBody EvaluationGcf evaluationGcf) {
        // Vérification de l'existence de l'évaluation avant de la mettre à jour
        return evaluationGcfService.updateEvaluationGcf(evaluationGcf);
    }

    // Supprimer une évaluation
    @ApiOperation(value = "Supprimer une évaluation de performance", notes = "Cette méthode permet de supprimer une évaluation de performance existante.")
    @DeleteMapping("/{indicateurPerformance}") // Utilisation de l'indicateurPerformance comme identifiant
    public void deleteEvaluation(@PathVariable String indicateurPerformance) {
        evaluationGcfService.deleteEvaluationGcf(indicateurPerformance);
    }

    // Récupérer une évaluation par ID (indicateurPerformance)
    @ApiOperation(value = "Récupérer une évaluation de performance", notes = "Cette méthode permet de récupérer une évaluation de performance par son indicateur de performance.")
    @GetMapping("/{indicateurPerformance}") // Utilisation de l'indicateurPerformance comme identifiant
    public Optional<EvaluationGcf> getEvaluationById(@PathVariable String indicateurPerformance) {
        return evaluationGcfService.getEvaluationGcfById(indicateurPerformance);
    }

    // Récupérer toutes les évaluations
    @ApiOperation(value = "Récupérer toutes les évaluations de performance", notes = "Cette méthode permet de récupérer la liste de toutes les évaluations de performance.")
    @GetMapping
    public Iterable<EvaluationGcf> getAllEvaluations() {
        return evaluationGcfService.getAllEvaluationGcf();
    }
}

