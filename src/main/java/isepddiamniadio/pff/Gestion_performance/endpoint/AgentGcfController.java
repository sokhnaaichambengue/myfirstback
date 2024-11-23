package isepddiamniadio.pff.Gestion_performance.endpoint;

import io.swagger.annotations.ApiOperation;
import isepddiamniadio.pff.Gestion_performance.entities.AgentCommercialisation;
import isepddiamniadio.pff.Gestion_performance.entities.AgentGcf;
import isepddiamniadio.pff.Gestion_performance.service.AgentCommercialisationService;
import isepddiamniadio.pff.Gestion_performance.service.AgentGcfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agentgcf")
public class AgentGcfController {

    @Autowired
    private AgentGcfService agentGcfService;

    // Ajouter un agent de commercialisation
    @ApiOperation(value = "Ajouter un agent de commercialisation", notes = "Cette méthode permet d'ajouter un agent de commercialisation avec ses données.")
    @PostMapping
    public AgentGcf addAgent(@RequestBody AgentGcf agentGcf) {
        return agentGcfService.addAgent(agentGcf);
    }

    // Mettre à jour un agent de commercialisation
    @ApiOperation(value = "Mettre à jour un agent de commercialisation", notes = "Cette méthode permet de mettre à jour les informations d'un agent de commercialisation.")
    @PutMapping("/{agentId}") // Correction de la route pour inclure l'ID
    public AgentGcf updateAgent(@PathVariable int agentId, @RequestBody AgentGcf updatedAgent) {
        return agentGcfService.updateAgent(agentId, updatedAgent);
    }

    // Supprimer un agent de commercialisation
    @ApiOperation(value = "Supprimer un agent de commercialisation", notes = "Cette méthode permet de supprimer un agent de commercialisation existant.")
    @DeleteMapping("/{agentId}") // Correction de la route pour inclure l'ID
    public void deleteAgent(@PathVariable int agentId) {
       agentGcfService.deleteAgent(agentId);
    }

    // Récupérer un agent de commercialisation par ID
    @ApiOperation(value = "Récupérer un agent de commercialisation", notes = "Cette méthode permet de récupérer les informations d'un agent par son ID.")
    @GetMapping("/{agentId}") // Correction de la route pour inclure l'ID
    public AgentGcf getAgentById(@PathVariable int agentId) {
        return agentGcfService.getAgentById(agentId);
    }

    // Récupérer tous les agents de commercialisation
    @ApiOperation(value = "Récupérer tous les agents de commercialisation", notes = "Cette méthode permet de récupérer la liste de tous les agents de commercialisation.")
    @GetMapping
    public Iterable<AgentGcf> getAllAgents() {
        return agentGcfService.getAllAgents();
    }
}
