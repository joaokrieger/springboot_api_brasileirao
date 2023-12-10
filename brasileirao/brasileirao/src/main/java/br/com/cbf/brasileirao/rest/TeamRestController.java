package br.com.cbf.brasileirao.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getTeam(@PathVariable(value = "id") Long teamId){
        Optional<Team> team = teamService.loadTeam(teamId);
        if(team.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found.");
            
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeams(){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.listTeams());
    }

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody TeamDto teamDto){ 
       return ResponseEntity.status(HttpStatus.CREATED).body(teamService.insertTeam(teamDto));
    }
}
