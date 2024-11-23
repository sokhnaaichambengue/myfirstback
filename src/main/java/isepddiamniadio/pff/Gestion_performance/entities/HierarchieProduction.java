package isepddiamniadio.pff.Gestion_performance.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HierarchieProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AgentProduction agentProduction;

    @ManyToOne
    private AgentProduction superieurHierarchique;

    private String roleHierarchique;
}



