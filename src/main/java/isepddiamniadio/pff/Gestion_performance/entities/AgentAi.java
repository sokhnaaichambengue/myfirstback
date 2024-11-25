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
public class AgentAi {
    @Id
    private int id_agent;
    private String prenomAgent;
    private String nomAgent;
    private String adresseAgent;
    private String telephone_agent;
    private String email_agent;


    @ManyToOne
    private Poste poste;  // Division à laquelle l'agent appartient

    @OneToMany(mappedBy = "agentAi", cascade = CascadeType.ALL)
    private List<ObjectifsAi> objectifsAis; // Objectifs spécifiques à cet agent
// Indicateurs spécifiques à cet agent

    @OneToMany(mappedBy = "agentAi", cascade = CascadeType.ALL)
    private List<MissionAi> missionAis;  // Missions spécifiques à cet agent
    @OneToMany(mappedBy = "agentAi", cascade = CascadeType.ALL)
    private List<HierarchieAi> hierarchieAis;
    @OneToMany(mappedBy = "agentAi", cascade = CascadeType.ALL)
    private List<CompetenceAi> competenceAis;
    @OneToMany(mappedBy = "agentAi", cascade = CascadeType.ALL)
    private List<EvaluationnPerformanceAi> evaluationnPerformanceAis;
    @OneToMany(mappedBy = "agentAi", cascade = CascadeType.ALL)
    private List<IndicateurPerformanceAi> indicateurPerformanceAis;





}
