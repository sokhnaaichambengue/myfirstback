package isepddiamniadio.pff.Gestion_performance.service;

import isepddiamniadio.pff.Gestion_performance.dao.*;
import isepddiamniadio.pff.Gestion_performance.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AgentCommercialisationService {

    @Autowired
    private AgentCommercialisationRepository agentCommercialisationRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private HierarchieCommercialisationRepository hierarchieCommercialisationRepository;

    @Autowired
    private EvaluationPerformanceAgentCommRepository evaluationPerformanceAgentCommRepository;

    // Ajouter un agent de commercialisation avec ses objectifs, missions, hiérarchies et compétences
    @Transactional
    public AgentCommercialisation addAgent(AgentCommercialisation agentCommercialisation) {
        // Vérification du poste, et affectation du poste par défaut si nécessaire
        if (agentCommercialisation.getPoste() == null || agentCommercialisation.getPoste().getId() == 0) {
            Optional<Poste> posteOptional = posteRepository.findById(1); // Poste par défaut
            if (posteOptional.isPresent()) {
                agentCommercialisation.setPoste(posteOptional.get());
            } else {
                throw new RuntimeException("Le poste par défaut n'existe pas. Impossible d'ajouter l'agent.");
            }
        }

        // Sauvegarde de l'agent dans la base de données
        agentCommercialisation = agentCommercialisationRepository.save(agentCommercialisation);

        // Sauvegarde des associations (objectifs, missions, hiérarchies, compétences, évaluations)
        saveAssociations(agentCommercialisation);

        return agentCommercialisation;
    }

    // Méthode générique pour sauvegarder les associations
    private void saveAssociations(AgentCommercialisation agentCommercialisation) {
        // Sauvegarder les hiérarchies commerciales associées
        if (agentCommercialisation.getHierarchieCommercialisations() != null) {
            for (HierarchieCommercialisation hierarchie : agentCommercialisation.getHierarchieCommercialisations()) {
                hierarchie.setAgentCommercialisation(agentCommercialisation);
                hierarchieCommercialisationRepository.save(hierarchie);
            }
        }

        // Sauvegarder les évaluations de performance
        if (agentCommercialisation.getEvaluationPerformanceAgentCommercialisations() != null) {
            for (EvaluationPerformanceAgentComm evaluation : agentCommercialisation.getEvaluationPerformanceAgentCommercialisations()) {
                evaluation.setAgentCommercialisation(agentCommercialisation);
                evaluationPerformanceAgentCommRepository.save(evaluation);
            }
        }
    }

    // Mettre à jour un agent de commercialisation existant
    @Transactional
    public AgentCommercialisation updateAgent(int agentId, AgentCommercialisation updatedAgent) {
        Optional<AgentCommercialisation> existingAgentOpt = agentCommercialisationRepository.findById(agentId);
        if (existingAgentOpt.isPresent()) {
            AgentCommercialisation existingAgent = existingAgentOpt.get();

            // Mise à jour des informations de l'agent
            existingAgent.setPrenomAgent(updatedAgent.getPrenomAgent());
            existingAgent.setNomAgent(updatedAgent.getNomAgent());
            existingAgent.setAdresseAgent(updatedAgent.getAdresseAgent());
            existingAgent.setTelephone_agent(updatedAgent.getTelephone_agent());
            existingAgent.setEmail_agent(updatedAgent.getEmail_agent());

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
            existingAgent = agentCommercialisationRepository.save(existingAgent);

            // Mise à jour des associations
            saveAssociations(existingAgent);

            return existingAgent;
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Supprimer un agent de commercialisation avec ses entités associées
    @Transactional
    public void deleteAgent(int agentId) {
        Optional<AgentCommercialisation> agentOpt = agentCommercialisationRepository.findById(agentId);
        if (agentOpt.isPresent()) {
            AgentCommercialisation agentCommercialisation = agentOpt.get();

            // Supprimer les entités associées
            deleteAssociations(agentCommercialisation);

            // Supprimer l'agent
            agentCommercialisationRepository.delete(agentCommercialisation);
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Méthode générique pour supprimer les associations
    private void deleteAssociations(AgentCommercialisation agentCommercialisation) {
        if (agentCommercialisation.getHierarchieCommercialisations() != null) {
            hierarchieCommercialisationRepository.deleteAll(agentCommercialisation.getHierarchieCommercialisations());
        }
        if (agentCommercialisation.getEvaluationPerformanceAgentCommercialisations() != null) {
            evaluationPerformanceAgentCommRepository.deleteAll(agentCommercialisation.getEvaluationPerformanceAgentCommercialisations());
        }
    }

    // Récupérer un agent de commercialisation par son ID
    public AgentCommercialisation getAgentById(int agentId) {
        return agentCommercialisationRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent de commercialisation non trouvé"));
    }

    // Récupérer tous les agents de commercialisation
    public Iterable<AgentCommercialisation> getAllAgents() {
        return agentCommercialisationRepository.findAll();
    }
}
