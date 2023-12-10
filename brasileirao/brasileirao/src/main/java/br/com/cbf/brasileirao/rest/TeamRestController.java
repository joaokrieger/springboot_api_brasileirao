package br.com.cbf.brasileirao.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbf.brasileirao.dtos.TeamDto;
import br.com.cbf.brasileirao.models.Team;
import br.com.cbf.brasileirao.services.TeamService;

@RestController
@RequestMapping(value = "/teams")
public class TeamRestController {
    
    @Autowired
    private TeamService teamService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Team> getTeam(@PathVariable Long teamId){
        return ResponseEntity.ok().body(teamService.loadTeam(teamId));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeams(){
        return ResponseEntity.ok().body(teamService.listTeams());
    }

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody TeamDto teamDto){
        return ResponseEntity.ok().body(teamService.insertTeam(teamDto)); 
    }
}
