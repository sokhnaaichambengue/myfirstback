package isepddiamniadio.pff.Gestion_performance.service;

import isepddiamniadio.pff.Gestion_performance.dao.*;
import isepddiamniadio.pff.Gestion_performance.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgentGRHService {
    @Autowired
    private AgentGRHRepository agentGRHRepository;

    @Autowired
    private ObjectifsGRHRepository objectifsGRHRepository;

    @Autowired
    private MissionGRHRepository missionGRHRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private HierarchieGrHRepository hierhieGrHRepository;
    @Autowired
    private CompetenceGRHRepository competenceGRHRepository;
    @Autowired
    private EvaluationAgentGrhRepository evaluationAgentGRHRepository;

    // Ajouter un agent de commercialisation avec ses objectifs, missions et indicateurs
    public AgentGrh addAgent(AgentGrh agentGrh) {
        new Poste(2, "commercialisation");
        // Vérification si le poste de l'agent est nul
        if (agentGrh.getPoste() == null || agentGrh.getPoste().getId() == 0) {
            // Si le poste n'est pas défini, récupérer un poste par défaut (par exemple avec ID 1)
            Optional<Poste> posteOptional = posteRepository.findById(1); // Poste par défaut
            if (posteOptional.isPresent()) {
                Poste poste = posteOptional.get();
                // Associer le poste à l'agent
                agentGrh.setPoste(poste);
            } else {
                throw new RuntimeException("Le poste par défaut n'existe pas. Impossible d'ajouter l'agent.");
            }
        }

        // Sauvegarde de l'agent dans la base de données
        agentGRHRepository.save(agentGrh);

        // Sauvegarde des objectifs commerciaux associés
        if (agentGrh.getMissionGrhs()!= null) {
            for (ObjectifsGrh objectifsGRH : agentGrh.getObjectifsGrhs()) {
                objectifsGRH.setAgentGrh(agentGrh);
               objectifsGRHRepository.save(objectifsGRH);
            }
        }
        if (agentGrh.getHierarchieGrhs() != null) {
            for (HierarchieGrh hierarchieGrH : agentGrh.getHierarchieGrhs()) {
               hierarchieGrH.setAgentGrh(agentGrh);
               hierhieGrHRepository.save(hierarchieGrH);

            }
        }

        // Sauvegarde de la hiérarchie commerciale associée


        // Sauvegarde des missions commerciales associées
        if (agentGrh.getMissionGrhs()!= null) {
            for (MissionGrh missionGRH: agentGrh.getMissionGrhs()) {
              missionGRH.setAgentGrh(agentGrh);
                missionGRHRepository.save(missionGRH);
            }
        }
        if (agentGrh.getCompetenceGrhs()!= null) {
            for (CompetenceGrh competenceGRH : agentGrh.getCompetenceGrhs()) {
                competenceGRH.setAgentGrh(agentGrh);
             competenceGRHRepository.save(competenceGRH);
            }
        }
        if (agentGrh.getEvaluationAgentGrhs()!= null) {
            for (EvaluationAgentGrh evaluationAgentGrh : agentGrh.getEvaluationAgentGrhs()) {
                evaluationAgentGrh.setAgentGrh(agentGrh);
                evaluationAgentGRHRepository.save(evaluationAgentGrh);

            }
        }

        return null;

    }

    // Mettre à jour un agent de commercialisation existant
    public AgentGrh updateAgent(int agentId, AgentGrh updatedAgent) {
        Optional<AgentGrh> existingAgentOpt = agentGRHRepository.findById(agentId);
        if (existingAgentOpt.isPresent()) {
            AgentGrh existingAgent = existingAgentOpt.get();

            // Mise à jour des informations de l'agent
            existingAgent.setPrenomAgent(updatedAgent.getPrenomAgent());
            existingAgent.setNomAgent(updatedAgent.getNomAgent());
            existingAgent.setAdresseAgent(updatedAgent.getAdresseAgent());
            existingAgent.setTelephoneAgent(updatedAgent.getTelephoneAgent());
            existingAgent.setEmailAgent(updatedAgent.getEmailAgent());

            // Vérifier si un poste est fourni et s'il est valide
            if (updatedAgent.getPoste() != null && updatedAgent.getPoste().getId() != 0) {
                // Recherche du poste dans la base de données
                Optional<Poste> posteOptional = posteRepository.findById(updatedAgent.getPoste().getId());
                if (posteOptional.isPresent()) {
                    Poste poste = posteOptional.get();
                    existingAgent.setPoste(poste);
                } else {
                    throw new RuntimeException("Le poste spécifié n'existe pas");
                }
            }

            // Sauvegarde de l'agent mis à jour
            return agentGRHRepository.save(existingAgent);
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Supprimer un agent de commercialisation avec ses entités associées
    public void deleteAgent(int agentId) {
        Optional<AgentGrh> agentOpt = agentGRHRepository.findById(agentId);
        if (agentOpt.isPresent()) {
           AgentGrh agentGRH = agentOpt.get();

            // Supprimer les entités associées (Objectifs, Missions, Hiérarchie)
           objectifsGRHRepository.deleteAll(agentGRH.getObjectifsGrhs());
            missionGRHRepository.deleteAll(agentGRH.getMissionGrhs());
           hierhieGrHRepository.deleteAll(agentGRH.getHierarchieGrhs());
          competenceGRHRepository.deleteAll(agentGRH.getCompetenceGrhs());
          evaluationAgentGRHRepository.deleteAll(agentGRH.getEvaluationAgentGrhs());

            // Supprimer l'agent
         agentGRHRepository.delete(agentGRH);
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Récupérer un agent de commercialisation par son ID
    public AgentGrh getAgentById(int agentId) {
        return agentGRHRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent de commercialisation non trouvé"));
    }

    // Récupérer tous les agents de commercialisation
    public Iterable<AgentGrh> getAllAgents() {
        return agentGRHRepository.findAll();
    }
}