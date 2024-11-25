package isepddiamniadio.pff.Gestion_performance.service;

import isepddiamniadio.pff.Gestion_performance.dao.*;
import isepddiamniadio.pff.Gestion_performance.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AgentAiService {

    @Autowired
    private AgentAiRepository agentAiRepository;

    @Autowired
    private ObjectifsAiRepository objectifsAiRepository;

    @Autowired
    private MissionAiRepository missionAiRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private HierarchieAiRepository hierarchieAiRepository;

    @Autowired
    private CompetenceAiRepository competenceAiRepository;
    @Autowired
    private EvaluationnPerformanceAiRepository evaluationnPerformanceAiRepository;
    @Autowired
    private IndicateurPerformanceAiRepository indicateurPerformanceAiRepository;

    // Ajouter un agent de commercialisation avec ses objectifs, missions, hiérarchies et compétences
    @Transactional
    public AgentAi addAgent(AgentAi agentAi) {
        // Vérification du poste, et affectation du poste par défaut si nécessaire
        if (agentAi.getPoste() == null || agentAi.getPoste().getId() == 0) {
            Optional<Poste> posteOptional = posteRepository.findById(1); // Poste par défaut
            if (posteOptional.isPresent()) {
               agentAi.setPoste(posteOptional.get());
            } else {
                throw new RuntimeException("Le poste par défaut n'existe pas. Impossible d'ajouter l'agent.");
            }
        }

        // Sauvegarde de l'agent dans la base de données
        agentAi = agentAiRepository.save(agentAi);

        // Sauvegarde des entités associées (objectifs, missions, hiérarchie, compétences)
        saveAssociations(agentAi);

        return agentAi;
    }

    // Méthode générique pour sauvegarder les associations
    private void saveAssociations(AgentAi agentAi) {
        // Sauvegarde des objectifs commerciaux associés
        if (agentAi.getObjectifsAis() != null) {
            for (ObjectifsAi  objectifsAi : agentAi.getObjectifsAis()) {
             objectifsAi.setAgentAi(agentAi);
               objectifsAiRepository.save(objectifsAi);
            }
        }

        // Sauvegarde des hiérarchies commerciales associées
        if (agentAi.getHierarchieAis() != null) {
            for (HierarchieAi hierarchieAi : agentAi.getHierarchieAis()) {
               hierarchieAi.setAgentAi(agentAi);
             hierarchieAiRepository.save(hierarchieAi);
            }
        }

        // Sauvegarde des missions commerciales associées
        if (agentAi.getMissionAis() != null) {
            for (MissionAi missionAi : agentAi.getMissionAis()) {
               missionAi.setAgentAi(agentAi);
            missionAiRepository.save(missionAi);
            }
        }

        // Sauvegarde des compétences commerciales associées
        if (agentAi.getCompetenceAis() != null) {
            for (CompetenceAi competenceAi : agentAi.getCompetenceAis()) {
                competenceAi.setAgentAi(agentAi);
                competenceAiRepository.save(competenceAi);
            }
        }
        if (agentAi.getCompetenceAis() != null) {
            for (CompetenceAi competenceAi : agentAi.getCompetenceAis()) {
                competenceAi.setAgentAi(agentAi);
                competenceAiRepository.save(competenceAi);
            }
        }

        if (agentAi.getEvaluationnPerformanceAis()!= null) {
            for (EvaluationnPerformanceAi evaluationnPerformanceAi : agentAi.getEvaluationnPerformanceAis()) {
               evaluationnPerformanceAi.setAgentAi(agentAi);
               evaluationnPerformanceAiRepository.save(evaluationnPerformanceAi);

            }
        }
        if (agentAi.getIndicateurPerformanceAis()!= null) {
            for (IndicateurPerformanceAi indicateurPerformanceAi : agentAi.getIndicateurPerformanceAis()) {
               indicateurPerformanceAi.setAgentAi(agentAi);
               indicateurPerformanceAiRepository.save(indicateurPerformanceAi);

            }
        }
    }

    // Mettre à jour un agent de commercialisation existant
    @Transactional
    public AgentAi updateAgent(int agentId, AgentAi updatedAgent) {
        Optional<AgentAi> existingAgentOpt = agentAiRepository.findById(agentId);
        if (existingAgentOpt.isPresent()) {
           AgentAi existingAgent = existingAgentOpt.get();

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
            existingAgent = agentAiRepository.save(existingAgent);

            // Mise à jour des entités associées (objectifs, missions, hiérarchies, compétences)
            saveAssociations(existingAgent);

            return existingAgent;
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Supprimer un agent de commercialisation avec ses entités associées
    @Transactional
    public void deleteAgent(int agentId) {
        Optional<AgentAi> agentOpt = agentAiRepository.findById(agentId);
        if (agentOpt.isPresent()) {
          AgentAi agentAi = agentOpt.get();

            // Supprimer les entités associées
            deleteAssociations(agentAi);

            // Supprimer l'agent
            agentAiRepository.delete(agentAi);
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Méthode générique pour supprimer les associations
    private void deleteAssociations(AgentAi agentAi) {
        if (agentAi.getObjectifsAis()!= null) {
           objectifsAiRepository.deleteAll(agentAi.getObjectifsAis());
        }
        if (agentAi.getMissionAis()!= null) {
            missionAiRepository.deleteAll(agentAi.getMissionAis());
        }
        if (agentAi.getHierarchieAis()!= null) {
         hierarchieAiRepository.deleteAll(agentAi.getHierarchieAis());
        }
        if (agentAi.getCompetenceAis() != null) {
         competenceAiRepository.deleteAll(agentAi.getCompetenceAis());
        }
        if (agentAi.getEvaluationnPerformanceAis()!= null) {
          evaluationnPerformanceAiRepository.deleteAll(agentAi.getEvaluationnPerformanceAis());
        }
        if (agentAi.getIndicateurPerformanceAis()!= null) {
           indicateurPerformanceAiRepository.deleteAll(agentAi.getIndicateurPerformanceAis());
        }
    }

    // Récupérer un agent de commercialisation par son ID
    public AgentAi getAgentById(int agentId) {
        return agentAiRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent de commercialisation non trouvé"));
    }

    // Récupérer tous les agents de commercialisation
    public Iterable<AgentAi> getAllAgents() {
        return agentAiRepository.findAll();
    }
}

