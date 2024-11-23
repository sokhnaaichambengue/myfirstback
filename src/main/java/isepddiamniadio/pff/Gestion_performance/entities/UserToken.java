package isepddiamniadio.pff.Gestion_performance.entities;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Getter
    @Setter
    @Entity
    @Table(name = "user_token")
    public class UserToken {

        @Id
        private String token;

        @ManyToOne
        @JoinColumn(nullable = false)
        private User user;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(nullable = false)
        private Date notBefore;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(nullable = false)
        private Date notAfter;



    }


