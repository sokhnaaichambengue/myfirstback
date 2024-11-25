package isepddiamniadio.pff.Gestion_performance.service;

import isepddiamniadio.pff.Gestion_performance.dao.*;
import isepddiamniadio.pff.Gestion_performance.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AgentGcfService {

    @Autowired
    private AgentGcfRepository agentGcfRepository;

    @Autowired
    private ObjectifsGcfRepository objectifsGcfRepository;

    @Autowired
    private MissionGcfRepository missionGcfRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private HierarchieGcfRepository hierieGcfRepository;

    @Autowired
    private CompetenceAgentGcfRepository competenceAgentGcfRepository;
    @Autowired
    private EvaluationAgentGcfRepository evaluationAgentGcfRepository;
    @Autowired
    private IndicateurPerformanceGcfRepository indicateurPerformanceGcfRepository;


    // Ajouter un agent de commercialisation avec ses objectifs, missions, hiérarchies et compétences
    @Transactional
    public AgentGcf addAgent(AgentGcf agentGcf) {
        // Vérification du poste, et affectation du poste par défaut si nécessaire
        if (agentGcf.getPoste() == null || agentGcf.getPoste().getId() == 0) {
            Optional<Poste> posteOptional = posteRepository.findById(1); // Poste par défaut
            if (posteOptional.isPresent()) {
                agentGcf.setPoste(posteOptional.get());
            } else {
                throw new RuntimeException("Le poste par défaut n'existe pas. Impossible d'ajouter l'agent.");
            }
        }

        // Sauvegarde de l'agent dans la base de données
 agentGcf = agentGcfRepository.save(agentGcf);

        // Sauvegarde des entités associées (objectifs, missions, hiérarchie, compétences)
        saveAssociations(agentGcf);

        return agentGcf;
    }

    // Méthode générique pour sauvegarder les associations
    private void saveAssociations(AgentGcf agentGcf) {
        // Sauvegarde des objectifs commerciaux associés
        if (agentGcf.getObjectifsGcfs() != null) {
            for (ObjectifsGcf objectifsGcf : agentGcf.getObjectifsGcfs()) {
               objectifsGcf.setAgentGcf(agentGcf);
              objectifsGcfRepository.save(objectifsGcf);
            }
        }

        // Sauvegarde des hiérarchies commerciales associées
        if (agentGcf.getHierarchieGcfs()!= null) {
            for (HierarchieGcf hierarchieGcf : agentGcf.getHierarchieGcfs()) {
               hierarchieGcf.setAgentGcf(agentGcf);
               hierieGcfRepository.save(hierarchieGcf);
            }
        }

        // Sauvegarde des missions commerciales associées
        if (agentGcf.getMissionGcfs() != null) {
            for (MissionGcf missionGcf : agentGcf.getMissionGcfs()) {
              missionGcf.setAgentGcf(agentGcf);
             missionGcfRepository.save(missionGcf);
            }
        }

        // Sauvegarde des compétences commerciales associées
        if (agentGcf.getCompetenceAgentGcfs() != null) {
            for (CompetenceAgentGcf competenceAgentGcf : agentGcf.getCompetenceAgentGcfs()) {
               competenceAgentGcf.setAgentGcf(agentGcf);
               competenceAgentGcfRepository.save(competenceAgentGcf);
            }
        }
        if (agentGcf.getIndicateurPerformanceGcfs()!= null) {
            for (IndicateurPerformanceGcf indicateurPerformanceGcf: agentGcf.getIndicateurPerformanceGcfs()) {
               indicateurPerformanceGcf.setAgentGcf(agentGcf);
               indicateurPerformanceGcfRepository.save(indicateurPerformanceGcf);
            }
        }
        if (agentGcf.getEvaluationAgentGcfs() != null) {
            for (EvaluationAgentGcf evaluationAgentGcf : agentGcf.getEvaluationAgentGcfs()) {
               evaluationAgentGcf.setAgentGcf(agentGcf);
               evaluationAgentGcfRepository.save(evaluationAgentGcf);
            }
        }
    }

    // Mettre à jour un agent de commercialisation existant
    @Transactional
    public AgentGcf updateAgent(int agentId, AgentGcf updatedAgent) {
        Optional<AgentGcf> existingAgentOpt = agentGcfRepository.findById(agentId);
        if (existingAgentOpt.isPresent()) {
           AgentGcf existingAgent = existingAgentOpt.get();

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
            existingAgent = agentGcfRepository.save(existingAgent);

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
        Optional<AgentGcf> agentOpt = agentGcfRepository.findById(agentId);
        if (agentOpt.isPresent()) {
          AgentGcf agentGcf = agentOpt.get();

            // Supprimer les entités associées
            deleteAssociations(agentGcf);

            // Supprimer l'agent
          agentGcfRepository.delete(agentGcf);
        } else {
            throw new RuntimeException("Agent de commercialisation non trouvé");
        }
    }

    // Méthode générique pour supprimer les associations
    private void deleteAssociations(AgentGcf agentGcf) {
        if (agentGcf.getObjectifsGcfs()!= null) {
          objectifsGcfRepository.deleteAll(agentGcf.getObjectifsGcfs());
        }
        if (agentGcf.getMissionGcfs()!= null) {
          missionGcfRepository.deleteAll(agentGcf.getMissionGcfs());
        }
        if (agentGcf.getHierarchieGcfs() != null) {
           hierieGcfRepository.deleteAll(agentGcf.getHierarchieGcfs());
        }
        if (agentGcf.getCompetenceAgentGcfs() != null) {
           competenceAgentGcfRepository.deleteAll(agentGcf.getCompetenceAgentGcfs());
        }
        if (agentGcf.getEvaluationAgentGcfs()!= null) {
           evaluationAgentGcfRepository.deleteAll(agentGcf.getEvaluationAgentGcfs());
        }

        if (agentGcf.getIndicateurPerformanceGcfs()!= null) {
        indicateurPerformanceGcfRepository.deleteAll(agentGcf.getIndicateurPerformanceGcfs());
        }
    }

    // Récupérer un agent de commercialisation par son ID
    public AgentGcf getAgentById(int agentId) {
        return agentGcfRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent de commercialisation non trouvé"));
    }

    // Récupérer tous les agents de commercialisation
    public Iterable<AgentGcf> getAllAgents() {
        return agentGcfRepository.findAll();
    }
}

