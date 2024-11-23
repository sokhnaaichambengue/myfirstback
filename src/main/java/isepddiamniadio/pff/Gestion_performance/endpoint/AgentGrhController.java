package isepddiamniadio.pff.Gestion_performance.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import isepddiamniadio.pff.Gestion_performance.entities.AgentGrh;
import isepddiamniadio.pff.Gestion_performance.service.AgentGRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(tags = "Agents de Commercialisation") // Tag pour regrouper les opérations sous "Agents de Commercialisation"
@RestController
@RequestMapping("/api/agentGrh")
public class AgentGrhController {

    @Autowired
    private AgentGRHService agentGRHService;

    @ApiOperation(value = "Ajouter un agent de commercialisation", notes = "Cette méthode permet d'ajouter un agent de commercialisation avec ses données.")
    @PostMapping
    public AgentGrh addAgent(@RequestBody AgentGrh agentGRH) {
        return agentGRHService.addAgent(agentGRH);
    }

    @ApiOperation(value = "Mettre à jour un agent de commercialisation", notes = "Cette méthode permet de mettre à jour les informations d'un agent de commercialisation.")
    @PutMapping("/{agentId}")
    public AgentGrh updateAgent(@PathVariable int agentId, @RequestBody AgentGrh updatedAgent) {
        return agentGRHService.updateAgent(agentId, updatedAgent);
    }

    @ApiOperation(value = "Supprimer un agent de commercialisation", notes = "Cette méthode permet de supprimer un agent de commercialisation existant.")
    @DeleteMapping("/{agentId}")
    public void deleteAgent(@PathVariable int agentId) {
       agentGRHService.deleteAgent(agentId);
    }

    @ApiOperation(value = "Récupérer un agent de commercialisation", notes = "Cette méthode permet de récupérer les informations d'un agent par son ID.")
    @GetMapping("/{agentId}")
    public AgentGrh getAgentById(@PathVariable int agentId) {
        return agentGRHService.getAgentById(agentId);
    }

    @ApiOperation(value = "Récupérer tous les agents de commercialisation", notes = "Cette méthode permet de récupérer la liste de tous les agents de commercialisation.")
    @GetMapping
    public Iterable<AgentGrh> getAllAgents() {
        return agentGRHService.getAllAgents();
    }
}

