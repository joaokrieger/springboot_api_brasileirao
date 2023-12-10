package br.com.cbf.brasileirao.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbf.brasileirao.services.MatchService;

@RestController
@RequestMapping(value = "/matches")
public class MatchRestController {
    
    @Autowired
    private MatchService matchService;

    @PostMapping(value = "generate-matches")
    public ResponseEntity<Void> generateMatchSchedule(){
        matchService.generateLeagueMatches(LocalDateTime.now());
        return ResponseEntity.ok().build();
    }
}
