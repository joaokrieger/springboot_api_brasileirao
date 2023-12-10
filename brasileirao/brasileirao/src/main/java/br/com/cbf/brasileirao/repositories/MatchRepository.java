package br.com.cbf.brasileirao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cbf.brasileirao.models.Match;
import br.com.cbf.brasileirao.models.Team;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByHomeTeamOrAwayTeam(Team homeTeam, Team awayTeam);
}