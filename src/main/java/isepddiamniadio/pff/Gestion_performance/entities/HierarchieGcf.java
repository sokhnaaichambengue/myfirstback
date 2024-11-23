package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HierarchieGcf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AgentGcf agentGcf;

    @ManyToOne
    private AgentGcf superieurHierarchique;

    private String roleHierarchique;
}
