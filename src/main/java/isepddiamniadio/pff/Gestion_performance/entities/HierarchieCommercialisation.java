package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HierarchieCommercialisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AgentCommercialisation agentCommercialisation;

    @ManyToOne
    private AgentCommercialisation superieurHierarchique;

    private String roleHierarchique;
}
