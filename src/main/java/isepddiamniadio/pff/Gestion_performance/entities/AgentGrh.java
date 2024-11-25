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
public class AgentGrh {
    @Id
    private int idAgent;
    private String prenomAgent;
    private String nomAgent;
    private String adresseAgent;
    private String telephoneAgent;
    private String emailAgent;
    @ManyToOne
    private Poste poste;
    @OneToMany(mappedBy = "agentGrh", cascade = CascadeType.ALL)
    private List<ObjectifsGrh> objectifsGrhs; // Objectifs spécifiques à cet agent
// Indicateurs spécifiques à cet agent

    @OneToMany(mappedBy = "agentGrh", cascade = CascadeType.ALL)
    private List<MissionGrh> missionGrhs;
    @OneToMany(mappedBy = "agentGrh", cascade = CascadeType.ALL)
    private List<HierarchieGrh> hierarchieGrhs;
    @OneToMany(mappedBy = "agentGrh", cascade = CascadeType.ALL)
    private List<CompetenceGrh> competenceGrhs;
    @OneToMany(mappedBy = "agentGrh", cascade = CascadeType.ALL)
    private List<IndicateursdePerformanceGrh> indicateursdePerformanceGrhs;
    @OneToMany(mappedBy = "agentGrh", cascade = CascadeType.ALL)
    private List<EvaluationAgentGrh> evaluationAgentGrhs;
  

}
