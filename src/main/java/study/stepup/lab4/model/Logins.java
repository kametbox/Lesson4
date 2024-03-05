package study.stepup.lab4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="logins")
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="acces_date")
    private Timestamp accesDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users userId;

    private String application;
}
