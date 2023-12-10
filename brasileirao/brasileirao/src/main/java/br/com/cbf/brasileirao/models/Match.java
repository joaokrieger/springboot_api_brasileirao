package br.com.cbf.brasileirao.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Match {    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homeTeam")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam")
    private Team awayTeam;

    private int homeTeamGoals;
    private int awayTeamGoals;
    private int audience;
    private int round;
    private LocalDateTime date;
}
