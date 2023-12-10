package br.com.cbf.brasileirao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cbf.brasileirao.dtos.TeamDto;
import br.com.cbf.brasileirao.models.Team;
import br.com.cbf.brasileirao.repositories.TeamRepository;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;

    public Team insertTeam(TeamDto teamDto){
        var team = new Team();
        BeanUtils.copyProperties(teamDto, team);
        return teamRepository.save(team);
    }

    public Optional<Team> loadTeam(Long teamId){
        return teamRepository.findById(teamId);
    }

    public List<Team> listTeams(){
        return teamRepository.findAll();
    }
}