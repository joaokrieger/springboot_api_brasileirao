package br.com.cbf.brasileirao.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbf.brasileirao.dtos.ClassificationDto;
import br.com.cbf.brasileirao.models.Match;
import br.com.cbf.brasileirao.services.MatchService;

@RestController
@RequestMapping(value = "/matches")
public class MatchRestController {
    
    @Autowired
    private MatchService matchService;

    @PostMapping(value = "/generate-matches")
    public ResponseEntity<Void> generateMatchSchedule(){
        matchService.generateLeagueMatches(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Match>> getMatches(){
        return ResponseEntity.ok().body(matchService.listMatches());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable(value = "id") Long matchId){
        return ResponseEntity.ok().body(matchService.loadMatch(matchId));
    }

    @GetMapping(value = "/classification")
    public ResponseEntity<List<ClassificationDto>> getClassification(){
        return ResponseEntity.ok().body(matchService.getClassification());
    }
}
