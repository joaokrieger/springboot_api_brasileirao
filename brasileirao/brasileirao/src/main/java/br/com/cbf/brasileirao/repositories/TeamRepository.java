package br.com.cbf.brasileirao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cbf.brasileirao.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{}