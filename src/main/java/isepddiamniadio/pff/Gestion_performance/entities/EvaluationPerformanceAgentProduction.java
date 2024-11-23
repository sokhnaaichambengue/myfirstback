package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EvaluationPerformanceAgentProduction {
    @Id
    private String indicateurPerformance;
    private String description;
    private String objectifPrduction;
    private String normeAgentProduction;
    @ManyToOne
    private AgentProduction agentProduction;

}
