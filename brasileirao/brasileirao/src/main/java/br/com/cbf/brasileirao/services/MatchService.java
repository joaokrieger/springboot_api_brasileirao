package br.com.cbf.brasileirao.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        int round = 0;

        for (int i = 0; i < countTeams - 1; i++) {
            round = i + 1;
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

                matches.add(generateMatch(matchday, round, homeTeam, awayTeam));
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
            matches2.add(generateMatch(match.getDate().plusDays(7 * matches.size()), match.getRound() + matches.size(), homeTeam, awayTeam));
        });
        matchRepository.saveAll(matches2);
    }

    private Match generateMatch(LocalDateTime matchday, int round, Team homeTeam, Team awayTeam) {
        
        Random random = new Random();

        Match match = new Match();
        match.setHomeTeam(homeTeam);
        match.setHomeTeamGoals(random.nextInt(5));
        match.setAwayTeam(awayTeam);
        match.setAwayTeamGoals(random.nextInt(5));
        match.setAudience(random.nextInt(100000));
        match.setRound(round);
        match.setDate(matchday);

        return match;
    }
}
