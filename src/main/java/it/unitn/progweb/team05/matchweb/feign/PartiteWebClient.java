package it.unitn.progweb.team05.matchweb.feign;

import it.unitn.progweb.team05.matchweb.schemas.MatchDTO;
import it.unitn.progweb.team05.matchweb.schemas.TeamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "partiteWeb", url = "http://localhost:8085/api")
public interface PartiteWebClient {

    @GetMapping("/matches")
    List<MatchDTO> getMatches(@RequestParam(value = "matchday", required = false) Integer matchday);

    @GetMapping("/results")
    Map<String, Integer> getResults(@RequestParam("matchday") Integer matchday);

    //get the list of teams from PartiteWeb
    @GetMapping("/teams")
    List<TeamDTO> getTeams();
}