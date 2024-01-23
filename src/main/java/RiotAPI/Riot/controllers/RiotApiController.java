package RiotAPI.Riot.controllers;

import RiotAPI.Riot.dtos.QuerySummonerDTO;
import RiotAPI.Riot.dtos.SummonerMasteryChampionsDTO;
import RiotAPI.Riot.dtos.SummonerTotalMasteryDTO;
import RiotAPI.Riot.services.FindPuuidService;
import RiotAPI.Riot.services.RiotApiService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("query-summoner")
public class RiotApiController {

    RiotApiService riotApiService = new RiotApiService();

    @GetMapping(value = "/{summoner}")
    public ResponseEntity<String> querySummoner(@PathVariable("summoner") String summoner){
        return riotApiService.querySummoner(summoner);
    }

    @GetMapping(value = "/{summoner}/mastery")
    public ResponseEntity<String> summonerMasteryChampions(@PathVariable("summoner") String summoner) {
        return riotApiService.summonerMasteryChampions(summoner);
    }

    @GetMapping(value = "/{summoner}/total-mastery")
    public ResponseEntity<String> summonerTotalMastery(@PathVariable("summoner") String summoner) {
        return riotApiService.summonerTotalMastery(summoner);
    }



    //https://br1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/FJu-fEUB1i23kXyZi2G_-wmos0p_Ii7Cg-3JW8cywIXNBkJujCExb1bqwnTvGAFJ-c5W_Ew-xGGiJA?api_key=RGAPI-e94bd0e4-3f2f-48e5-8fe9-4c143bf8e50c
}
