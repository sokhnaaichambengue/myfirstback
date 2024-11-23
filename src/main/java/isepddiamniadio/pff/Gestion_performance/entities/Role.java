package isepddiamniadio.pff.Gestion_performance.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    //Permet de facilier la creation d'un objet de classe, en utilisant une seul instruction.
    @Entity

    public class Role {

        @Id

        private String code;

        private String nom;

        @ManyToMany(mappedBy = "roles")
        @JsonIgnore
        private List<User> users;
    }

