package isepddiamniadio.pff.Gestion_performance.service;

import isepddiamniadio.pff.Gestion_performance.dao.*;
import isepddiamniadio.pff.Gestion_performance.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AgentProductionService {

    @Autowired
    private AgentProductionRepository agentProductionRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private HierarchieProductionRepository hierarchieProductionRepository;

    @Autowired
    private EvaluationPerformanceAgentProdRepository evaluationPerformanceAgentProdRepository;

    // Ajouter un agent de production avec ses objectifs, missions, hiérarchies et compétences
    @Transactional
    public AgentProduction addAgent(AgentProduction agentProduction) {
        // Vérification du poste, et affectation du poste par défaut si nécessaire
        if (agentProduction.getPoste() == null || agentProduction.getPoste().getId() == 0) {
            Optional<Poste> posteOptional = posteRepository.findById(1); // Poste par défaut
            if (posteOptional.isPresent()) {
                agentProduction.setPoste(posteOptional.get());
            } else {
                throw new RuntimeException("Le poste par défaut n'existe pas. Impossible d'ajouter l'agent.");
            }
        }

        // Sauvegarde de l'agent dans la base de données
        agentProduction = agentProductionRepository.save(agentProduction);

        // Sauvegarde des associations (objectifs, missions, hiérarchies, compétences, évaluations)
        saveAssociations(agentProduction);

        return agentProduction;
    }

    // Méthode générique pour sauvegarder les associations
    private void saveAssociations(AgentProduction agentProduction) {
        // Sauvegarder les hiérarchies associées
        if (agentProduction.getHierarchieProductions() != null) {
            for (HierarchieProduction hierarchie : agentProduction.getHierarchieProductions()) {
                hierarchie.setAgentProduction(agentProduction);
                hierarchieProductionRepository.save(hierarchie);
            }
        }

        // Sauvegarder les évaluations de performance
        if (agentProduction.getEvaluationPerformanceAgentProductions() != null) {
            for (EvaluationPerformanceAgentProduction evaluationPerformanceAgentProd : agentProduction.getEvaluationPerformanceAgentProductions()) {
                evaluationPerformanceAgentProd.setAgentProduction(agentProduction);
                evaluationPerformanceAgentProdRepository.save(evaluationPerformanceAgentProd);
            }
        }
    }

    // Mettre à jour un agent de production existant
    @Transactional
    public AgentProduction updateAgent(int agentId, AgentProduction updatedAgent) {
        Optional<AgentProduction> existingAgentOpt = agentProductionRepository.findById(agentId);
        if (existingAgentOpt.isPresent()) {
            AgentProduction existingAgent = existingAgentOpt.get();

            // Mise à jour des informations de l'agent
            existingAgent.setPrenomAgent(updatedAgent.getPrenomAgent());
            existingAgent.setNomAgent(updatedAgent.getNomAgent());
            existingAgent.setAdresseAgent(updatedAgent.getAdresseAgent());
            existingAgent.setTelephoneAgent(updatedAgent.getTelephoneAgent());
            existingAgent.setEmailAgent(updatedAgent.getEmailAgent());

            // Mise à jour du poste
            if (updatedAgent.getPoste() != null && updatedAgent.getPoste().getId() != 0) {
                Optional<Poste> posteOptional = posteRepository.findById(updatedAgent.getPoste().getId());
                if (posteOptional.isPresent()) {
                    existingAgent.setPoste(posteOptional.get());
                } else {
                    throw new RuntimeException("Le poste spécifié n'existe pas");
                }
            }

            // Sauvegarde de l'agent mis à jour
            existingAgent = agentProductionRepository.save(existingAgent);

            // Mise à jour des associations
            saveAssociations(existingAgent);

            return existingAgent;
        } else {
            throw new RuntimeException("Agent de production non trouvé");
        }
    }

    // Supprimer un agent de production avec ses entités associées
    @Transactional
    public void deleteAgent(int agentId) {
        Optional<AgentProduction> agentOpt = agentProductionRepository.findById(agentId);
        if (agentOpt.isPresent()) {
            AgentProduction agentProduction = agentOpt.get();

            // Supprimer les entités associées
            deleteAssociations(agentProduction);

            // Supprimer l'agent
            agentProductionRepository.delete(agentProduction);
        } else {
            throw new RuntimeException("Agent de production non trouvé");
        }
    }

    // Méthode générique pour supprimer les associations
    private void deleteAssociations(AgentProduction agentProduction) {
        if (agentProduction.getHierarchieProductions() != null) {
            hierarchieProductionRepository.deleteAll(agentProduction.getHierarchieProductions());
        }
        if (agentProduction.getEvaluationPerformanceAgentProductions() != null) {
            evaluationPerformanceAgentProdRepository.deleteAll(agentProduction.getEvaluationPerformanceAgentProductions());
        }
    }

    // Récupérer un agent de production par son ID
    public AgentProduction getAgentById(int agentId) {
        return agentProductionRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent de production non trouvé"));
    }

    // Récupérer tous les agents de production
    public Iterable<AgentProduction> getAllAgents() {
        return agentProductionRepository.findAll();
    }
}
