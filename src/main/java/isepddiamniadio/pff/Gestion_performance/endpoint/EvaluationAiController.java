package isepddiamniadio.pff.Gestion_performance.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationAi;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationGcf;
import isepddiamniadio.pff.Gestion_performance.entities.EvaluationnPerformanceAi;
import isepddiamniadio.pff.Gestion_performance.service.EvaluationAiService;
import isepddiamniadio.pff.Gestion_performance.service.EvaluationGcfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Api(tags = "Evaluations de Commercialisation") // Tag pour regrouper les opérations sous "Evaluations de Commercialisation"
@RestController
@RequestMapping("/api/evaluationai")
public class EvaluationAiController {

        @Autowired
        private EvaluationAiService evaluationAiService;

        // Ajouter une évaluation
        @ApiOperation(value = "Ajouter une évaluation de performance", notes = "Cette méthode permet d'ajouter une évaluation de performance pour un agent.")
        @PostMapping
        public EvaluationnPerformanceAi addEvaluation(@RequestBody EvaluationnPerformanceAi evaluationnPerformanceAi) {
            return evaluationAiService.saveEvaluationAi(evaluationnPerformanceAi);
        }

        // Mettre à jour une évaluation
        @ApiOperation(value = "Mettre à jour une évaluation de performance", notes = "Cette méthode permet de mettre à jour les informations d'une évaluation de performance.")
        @PutMapping("/{indicateurPerformance}") // Utilisation de l'indicateurPerformance comme identifiant
        public EvaluationnPerformanceAi updateEvaluation(@PathVariable String indicateurPerformance, @RequestBody EvaluationnPerformanceAi evaluationnPerformanceAi) {
            // Vérification de l'existence de l'évaluation avant de la mettre à jour
            return evaluationAiService.updateEvaluationAi(evaluationnPerformanceAi);
        }

        // Supprimer une évaluation
        @ApiOperation(value = "Supprimer une évaluation de performance", notes = "Cette méthode permet de supprimer une évaluation de performance existante.")
        @DeleteMapping("/{indicateurPerformance}") // Utilisation de l'indicateurPerformance comme identifiant
        public void deleteEvaluation(@PathVariable String indicateurPerformance) {
           evaluationAiService.deleteEvaluationAi(indicateurPerformance);
        }

        // Récupérer une évaluation par ID (indicateurPerformance)
        @ApiOperation(value = "Récupérer une évaluation de performance", notes = "Cette méthode permet de récupérer une évaluation de performance par son indicateur de performance.")
        @GetMapping("/{indicateurPerformance}") // Utilisation de l'indicateurPerformance comme identifiant
        public Optional<EvaluationnPerformanceAi> getEvaluationById(@PathVariable String indicateurPerformance) {
            return evaluationAiService.getEvaluationAiById(indicateurPerformance);
        }

        // Récupérer toutes les évaluations
        @ApiOperation(value = "Récupérer toutes les évaluations de performance", notes = "Cette méthode permet de récupérer la liste de toutes les évaluations de performance.")
        @GetMapping
        public Iterable<EvaluationnPerformanceAi> getAllEvaluations() {
            return evaluationAiService.getAllEvaluationAi();
        }
    }



