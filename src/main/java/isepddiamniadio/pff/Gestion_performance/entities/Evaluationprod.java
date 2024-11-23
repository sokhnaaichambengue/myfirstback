package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Evaluationprod {
    @Id
    private String indicateurPerformance;
    private String description;
    private String objectifgcf;
    private String normeAgentGcf;
    @ManyToOne
    private Poste poste;
}
