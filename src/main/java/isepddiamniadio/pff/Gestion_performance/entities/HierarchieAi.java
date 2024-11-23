package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HierarchieAi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AgentAi agentAi;

    @ManyToOne
    private AgentAi superieurHierarchique;

    private String roleHierarchique;
}