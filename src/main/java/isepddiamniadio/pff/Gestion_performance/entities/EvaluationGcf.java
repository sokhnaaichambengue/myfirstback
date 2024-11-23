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
public class EvaluationGcf {
    @Id
    private String indicateurPerformance;
    private String description;
    private String objectifgcf;
    private String normeAgentGcf;
    @ManyToOne
    private Poste poste;
}
