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
public class AgentProduction {
    @Id
    private int idAgent;
    private String prenomAgent;
    private String nomAgent;
    private String adresseAgent;
    private String telephoneAgent;
    private String emailAgent;

    @ManyToOne
    private Poste poste;

    @OneToMany(mappedBy = "agentProduction", cascade = CascadeType.ALL)
    private List<ObjectifsProduction> objectifsProductions;

    @OneToMany(mappedBy = "agentProduction", cascade = CascadeType.ALL)
    private List<MissionProduction> missionProductions;

    @OneToMany(mappedBy = "agentProduction", cascade = CascadeType.ALL)
    private List<HierarchieProduction> hierarchieProductions;

    @OneToMany(mappedBy = "agentProduction", cascade = CascadeType.ALL)
    private List<CompetenceAgentProduction> competenceAgentProductions;

    @OneToMany(mappedBy = "agentProduction", cascade = CascadeType.ALL)
    private List<EvaluationPerformanceAgentProduction> evaluationPerformanceAgentProductions;
    @OneToMany(mappedBy = "agentProduction", cascade = CascadeType.ALL)
    private List<IndicateursPerformanceProduction> indicateursperformanceProductions;


}

