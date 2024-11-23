package isepddiamniadio.pff.Gestion_performance.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isepddiamniadio.pff.Gestion_performance.entities.AgentCommercialisation;
import isepddiamniadio.pff.Gestion_performance.service.AgentCommercialisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Agents de Commercialisation") // Tag pour regrouper les opérations sous "Agents de Commercialisation"
@RestController
@RequestMapping("/api/agentcommercialisation")
public class AgentCommercialisationController {

    @Autowired
    private AgentCommercialisationService agentCommercialisationService;

    // Ajouter un agent de commercialisation
    @ApiOperation(value = "Ajouter un agent de commercialisation", notes = "Cette méthode permet d'ajouter un agent de commercialisation avec ses données.")
    @PostMapping
    public AgentCommercialisation addAgent(@RequestBody AgentCommercialisation agentCommercialisation) {
        return agentCommercialisationService.addAgent(agentCommercialisation);
    }

    // Mettre à jour un agent de commercialisation
    @ApiOperation(value = "Mettre à jour un agent de commercialisation", notes = "Cette méthode permet de mettre à jour les informations d'un agent de commercialisation.")
    @PutMapping("/{agentId}") // Correction de la route pour inclure l'ID
    public AgentCommercialisation updateAgent(@PathVariable int agentId, @RequestBody AgentCommercialisation updatedAgent) {
        return agentCommercialisationService.updateAgent(agentId, updatedAgent);
    }

    // Supprimer un agent de commercialisation
    @ApiOperation(value = "Supprimer un agent de commercialisation", notes = "Cette méthode permet de supprimer un agent de commercialisation existant.")
    @DeleteMapping("/{agentId}") // Correction de la route pour inclure l'ID
    public void deleteAgent(@PathVariable int agentId) {
        agentCommercialisationService.deleteAgent(agentId);
    }

    // Récupérer un agent de commercialisation par ID
    @ApiOperation(value = "Récupérer un agent de commercialisation", notes = "Cette méthode permet de récupérer les informations d'un agent par son ID.")
    @GetMapping("/{agentId}") // Correction de la route pour inclure l'ID
    public AgentCommercialisation getAgentById(@PathVariable int agentId) {
        return agentCommercialisationService.getAgentById(agentId);
    }

    // Récupérer tous les agents de commercialisation
    @ApiOperation(value = "Récupérer tous les agents de commercialisation", notes = "Cette méthode permet de récupérer la liste de tous les agents de commercialisation.")
    @GetMapping
    public Iterable<AgentCommercialisation> getAllAgents() {
        return agentCommercialisationService.getAllAgents();
    }
}
