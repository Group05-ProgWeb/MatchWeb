package it.unitn.progweb.team05.matchweb.services;

import it.unitn.progweb.team05.matchweb.exceptions.MultipleBetslipsException;
import it.unitn.progweb.team05.matchweb.schemas.BetSlip;
import it.unitn.progweb.team05.matchweb.schemas.BetSlipResult;
import it.unitn.progweb.team05.matchweb.schemas.Giornata;
import it.unitn.progweb.team05.matchweb.schemas.SingleBet;
import it.unitn.progweb.team05.matchweb.feign.PartiteWebClient;
import it.unitn.progweb.team05.matchweb.repositories.GiornataRepository;
import it.unitn.progweb.team05.matchweb.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CalcolaPunteggio {

    private final PartiteWebClient partiteWebClient;
    private final GiornataRepository giornataRepository;
    private final UserRepository userRepository;


    public CalcolaPunteggio(PartiteWebClient partiteWebClient, GiornataRepository giornataRepository, UserRepository userRepository) {
        this.partiteWebClient = partiteWebClient;
        this.giornataRepository = giornataRepository;
        this.userRepository = userRepository;
    }

    public BetSlipResult evaluateBetSlip(BetSlip betSlip) throws MultipleBetslipsException {
        if(giornataRepository.exists(betSlip.getMatchday())) {
            throw new MultipleBetslipsException();
        } else {
            giornataRepository.add(new Giornata(betSlip.getMatchday()));
            Map<String, Integer> results = partiteWebClient.getResults(betSlip.getMatchday());
            int score = 0;
            for(SingleBet b : betSlip.getBets()){
                if(b.getPrediction() == results.get(b.getMatchId())) {
                    score++;
                }
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            userRepository.updateScoreByUsername(authentication.getName(), userRepository.get(authentication.getName()).getTotalScore() + score);

            return new BetSlipResult(score);
        }
    }
}
