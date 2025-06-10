package it.unitn.progweb.team05.matchweb.services;

import it.unitn.progweb.team05.matchweb.models.MatchDTO;
import it.unitn.progweb.team05.matchweb.feign.PartiteWebClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class MatchService {

    private final PartiteWebClient partiteWebClient;

    private static final LocalDate TOURNAMENT_START_DAY = LocalDate.of(2025, 6, 8);

    public MatchService(PartiteWebClient partiteWebClient) {
        this.partiteWebClient = partiteWebClient;
    }

    public int getCurrentMatchDay() {
        return Math.toIntExact(ChronoUnit.DAYS.between(TOURNAMENT_START_DAY, LocalDate.now()));
    }
    public List<MatchDTO> getMatchesFromCurrentMatchDay() {
        return partiteWebClient.getMatches(getCurrentMatchDay());
    }
}
