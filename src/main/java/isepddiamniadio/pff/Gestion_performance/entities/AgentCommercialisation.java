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
public class AgentCommercialisation {
    @Id
    private int id_agent;
    private String prenomAgent;
    private String nomAgent;
    private String adresseAgent;
    private String telephone_agent;
    private String email_agent;
    
    @ManyToOne
    private Poste poste;  // Division à laquelle l'agent appartient


    @OneToMany(mappedBy = "agentCommercialisation", cascade = CascadeType.ALL)
    private List<ObjectifsCommercialisation>  objectifsCommercialisations; // Objectifs spécifiques à cet agent
// Indicateurs spécifiques à cet agent

    @OneToMany(mappedBy = "agentCommercialisation", cascade = CascadeType.ALL)
    private List<MissionCommercialisation> missionCommercialisations;  // Missions spécifiques à cet agent
    @OneToMany(mappedBy = "agentCommercialisation", cascade = CascadeType.ALL)
    private List<HierarchieCommercialisation> hierarchieCommercialisations;
    @OneToMany(mappedBy = "agentCommercialisation", cascade = CascadeType.ALL)
    private List<CompetenceAgentCommercialisation> competenceAgentCommercialisations;
    @OneToMany(mappedBy = "agentCommercialisation", cascade = CascadeType.ALL)
    private List<EvaluationPerformanceAgentComm> EvaluationPerformanceAgentCommercialisations;
    @OneToMany(mappedBy = "agentCommercialisation", cascade = CascadeType.ALL)
    private List<IndicateursPerformanceComm> IndicateursPerformanceCommercialisations;





}



