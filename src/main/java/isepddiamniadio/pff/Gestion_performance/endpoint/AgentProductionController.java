package isepddiamniadio.pff.Gestion_performance.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isepddiamniadio.pff.Gestion_performance.entities.AgentProduction;
import isepddiamniadio.pff.Gestion_performance.service.AgentCommercialisationService;
import isepddiamniadio.pff.Gestion_performance.service.AgentProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = "Agents de Commercialisation") // Tag pour regrouper les opérations sous "Agents de Commercialisation"
@RestController
@RequestMapping("/api/agentproduction")
public class AgentProductionController {
    @Autowired
    private AgentCommercialisationService agentCommercialisationService;
    @Autowired
    private AgentProductionService  agentProductionService;

    @ApiOperation(value = "Ajouter un agent de commercialisation", notes = "Cette méthode permet d'ajouter un agent de commercialisation avec ses données.")
    @PostMapping
    public AgentProduction addAgent(@RequestBody AgentProduction agentProduction) {
        return agentProductionService.addAgent(agentProduction);
    }

    @ApiOperation(value = "Mettre à jour un agent de commercialisation", notes = "Cette méthode permet de mettre à jour les informations d'un agent de commercialisation.")
    @PutMapping("/{agentId}")
    public AgentProduction updateAgent(@PathVariable int agentId, @RequestBody AgentProduction updatedAgent) {
        return agentProductionService.updateAgent(agentId, updatedAgent);
    }

    @ApiOperation(value = "Supprimer un agent de commercialisation", notes = "Cette méthode permet de supprimer un agent de commercialisation existant.")
    @DeleteMapping("/{agentId}")
    public void deleteAgent(@PathVariable int agentId) {
       agentProductionService.deleteAgent(agentId);
    }

    @ApiOperation(value = "Récupérer un agent de commercialisation", notes = "Cette méthode permet de récupérer les informations d'un agent par son ID.")
    @GetMapping("/{agentId}")
    public AgentProduction getAgentById(@PathVariable int agentId) {
        return agentProductionService.getAgentById(agentId);
    }

    @ApiOperation(value = "Récupérer tous les agents de commercialisation", notes = "Cette méthode permet de récupérer la liste de tous les agents de commercialisation.")
    @GetMapping
    public Iterable<AgentProduction> getAllAgents() {
        return agentProductionService.getAllAgents();
    }
}


