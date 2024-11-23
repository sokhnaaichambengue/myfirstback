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
public class EvaluationPerformanceDivisioncomm {
    @Id
    private String indicateurPerformance;
    private String description;
    private String objectifCommercialisation;
    private String normedivision;
    @ManyToOne
    private AgentCommercialisation agentCommercialisation;
    

}
