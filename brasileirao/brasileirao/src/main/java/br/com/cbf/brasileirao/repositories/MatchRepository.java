package br.com.cbf.brasileirao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cbf.brasileirao.models.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {}