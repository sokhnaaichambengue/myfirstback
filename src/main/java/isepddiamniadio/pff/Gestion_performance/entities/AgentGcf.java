package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentGcf {
    @Id
    private int id_agent;
    private String prenomAgent;
    private String nomAgent;
    private String adresseAgent;
    private String telephone_agent;
    private String email_agent;


    @ManyToOne
    private Poste poste;  // Division à laquelle l'agent appartient

    @OneToMany(mappedBy = "agentGcf", cascade = CascadeType.ALL)
    private List<ObjectifsGcf> objectifsGcfs; // Objectifs spécifiques à cet agent
// Indicateurs spécifiques à cet agent

    @OneToMany(mappedBy = "agentGcf", cascade = CascadeType.ALL)
    private List<MissionGcf> missionGcfs;  // Missions spécifiques à cet agent
    @OneToMany(mappedBy = "agentGcf", cascade = CascadeType.ALL)
    private List<HierarchieGcf> hierarchieGcfs;
    @OneToMany(mappedBy = "agentGcf", cascade = CascadeType.ALL)
    private List<CompetenceAgentGcf> competenceAgentGcfs;
    @OneToMany(mappedBy = "agentGcf", cascade = CascadeType.ALL)
    private List<EvaluationAgentGcf> evaluationAgentGcfs;
    @OneToMany(mappedBy = "agentGcf", cascade = CascadeType.ALL)
    private List<IndicateurPerformanceGcf> indicateurPerformanceGcfs;




}




