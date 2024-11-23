package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HierarchieGrh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AgentGrh agentGrh;

    @ManyToOne
    private AgentGrh superieurHierarchique;

    private String roleHierarchique;
}
