package br.com.cbf.brasileirao.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cbf.brasileirao.dtos.ClassificationDto;
import br.com.cbf.brasileirao.models.Match;
import br.com.cbf.brasileirao.models.Team;
import br.com.cbf.brasileirao.repositories.MatchRepository;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamService teamService;

    public void generateLeagueMatches(LocalDateTime firstMatchday){

        final List<Team> teams = teamService.listTeams();

        matchRepository.deleteAll();

        List<Match> matches = new ArrayList<>();

        int countTeams = teams.size();
        int countMatches = teams.size() / 2;

        LocalDateTime matchday = firstMatchday;
        int matchweek = 0;

        for (int i = 0; i < countTeams - 1; i++) {
            matchweek = i + 1;
            for (int j = 0; j < countMatches; j++) {

                Team homeTeam;
                Team awayTeam;

                if (j % 2 == 1 || i % 2 == 1 && j == 0) {
                    homeTeam = teams.get(countTeams - j - 1);
                    awayTeam = teams.get(j);
                } else {
                    homeTeam = teams.get(j);
                    awayTeam = teams.get(countTeams - j - 1);
                }

                matches.add(generateMatch(matchday, matchweek, homeTeam, awayTeam));
                matchday = matchday.plusDays(7);
            }
            teams.add(1, teams.remove(teams.size() - 1));
        }

        matches.forEach(jogo -> System.out.println(jogo));
        matchRepository.saveAll(matches);

        List<Match> matches2 = new ArrayList<>();
        matches.forEach(match -> {
            Team homeTeam = match.getHomeTeam();
            Team awayTeam = match.getAwayTeam();
            matches2.add(generateMatch(match.getDate().plusDays(7 * matches.size()), match.getMatchweek() + matches.size(), homeTeam, awayTeam));
        });
        matchRepository.saveAll(matches2);
    }

    private Match generateMatch(LocalDateTime matchday, int matchweek, Team homeTeam, Team awayTeam) {
        
        Random random = new Random();

        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setHomeTeamGoals(random.nextInt(5));
        match.setAwayTeam(awayTeam);
        match.setAwayTeamGoals(random.nextInt(5));
        match.setAudience(random.nextInt(100000));
        match.setMatchweek(matchweek);
        match.setDate(matchday);

        return match;
    }

    public List<Match> listMatches() {
        return matchRepository.findAll();
    }

    public Match loadMatch(Long matchId){
        return matchRepository.findById(matchId).get();
    }

    public List<ClassificationDto> getClassification() {

        final List<Team> teams = teamService.listTeams();
        List<ClassificationDto> classificationDtos = new ArrayList<>();

        teams.forEach(team ->{

            AtomicInteger victories = new AtomicInteger(0);
            AtomicInteger draws = new AtomicInteger(0);
            AtomicInteger defeats = new AtomicInteger(0);
            AtomicInteger goalsScored = new AtomicInteger(0);
            AtomicInteger goalsConceded = new AtomicInteger(0);

            final List<Match> matches = matchRepository.findByHomeTeamOrAwayTeam(team, team);
            matches.forEach(match ->{
                
                if(match.getHomeTeam() == team){
                  
                    if(match.getHomeTeamGoals() > match.getAwayTeamGoals()){
                        victories.getAndSet(victories.get() + 1);
                    }
                    else if(match.getHomeTeamGoals() < match.getAwayTeamGoals()){
                        defeats.getAndSet(defeats.get() + 1);
                    }
                    else{
                        draws.getAndSet(draws.get() + 1);
                    }

                    goalsScored.getAndSet(goalsScored.get() + match.getHomeTeamGoals()); 
                    goalsConceded.getAndSet(goalsScored.get() + match.getAwayTeamGoals()); 
                }
                else{

                    if(match. getAwayTeamGoals() > match.getHomeTeamGoals()){
                        victories.getAndSet(victories.get() + 1);
                    }
                    else if(match.getAwayTeamGoals() < match.getHomeTeamGoals()){
                        defeats.getAndSet(defeats.get() + 1);
                    }
                    else{
                        draws.getAndSet(draws.get() + 1);
                    }
                    
                    goalsScored.getAndSet(goalsScored.get() + match.getAwayTeamGoals()); 
                    goalsConceded.getAndSet(goalsScored.get() + match.getHomeTeamGoals()); 
                }
            });
            
            ClassificationDto classificationDto = new ClassificationDto();
            classificationDto.setVictories(victories.get());
            classificationDto.setDefeats(defeats.get());
            classificationDto.setDraws(draws.get());
            classificationDto.setGames(victories.get() + defeats.get() + draws.get());
            classificationDto.setGoalsConceded(goalsConceded.get());
            classificationDto.setGoalsScored(goalsScored.get());
            classificationDto.setTeam(team.getName());
            classificationDto.setTeamId(team.getId());
            classificationDto.setPoints((victories.get() * 3) + draws.get());
            classificationDtos.add(classificationDto);
        });

        return classificationDtos;
    }
}
