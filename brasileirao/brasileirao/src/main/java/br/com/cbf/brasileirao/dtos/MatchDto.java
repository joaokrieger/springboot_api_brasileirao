package br.com.cbf.brasileirao.dtos;

import java.time.LocalDateTime;

import br.com.cbf.brasileirao.models.Team;
import lombok.Data;

@Data
public class MatchDto {
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private int audience;
    private int matchweek;
    private LocalDateTime date;
}
