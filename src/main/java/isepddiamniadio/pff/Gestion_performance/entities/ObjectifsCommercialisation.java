package isepddiamniadio.pff.Gestion_performance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ObjectifsCommercialisation {
    @Id
    private int id;

    private String Description;

    @ManyToOne
    private AgentCommercialisation agentCommercialisation;

}
