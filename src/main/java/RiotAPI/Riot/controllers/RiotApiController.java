package RiotAPI.Riot.controllers;

import RiotAPI.Riot.dtos.QuerySummonerDTO;
import RiotAPI.Riot.dtos.SummonerMasteryChampionsDTO;
import RiotAPI.Riot.dtos.SummonerTotalMasteryDTO;
import RiotAPI.Riot.services.FindPuuidService;
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

    @GetMapping(value = "/{summoner}")
    public QuerySummonerDTO querySummoner(@PathVariable("summoner") String summoner){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<QuerySummonerDTO> resp =
                restTemplate
                        .getForEntity(
                                String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=RGAPI-e94bd0e4-3f2f-48e5-8fe9-4c143bf8e50c", summoner),
                                QuerySummonerDTO.class);


        return resp.getBody();
    }

    @GetMapping(value = "/{summoner}/mastery")
    public List<SummonerMasteryChampionsDTO> summonerMaestryChampions(@PathVariable("summoner") String summoner) {
        RestTemplate restTemplate = new RestTemplate();
        FindPuuidService findPuuidService = new FindPuuidService();

        String puuid = findPuuidService.getPuuidFromApi(summoner);

        ResponseEntity<List<SummonerMasteryChampionsDTO>> resp =
                restTemplate.exchange(
                        String.format("https://br1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/%s?api_key=RGAPI-e94bd0e4-3f2f-48e5-8fe9-4c143bf8e50c", puuid),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<SummonerMasteryChampionsDTO>>() {}
                );

        return resp.getBody();
    }

    @GetMapping(value = "/{summoner}/total-mastery")
    public SummonerTotalMasteryDTO summonerTotalMastery(@PathVariable("summoner") String summoner) {
        RestTemplate restTemplate = new RestTemplate();
        FindPuuidService findPuuidService = new FindPuuidService();

        String puuid = findPuuidService.getPuuidFromApi(summoner);

        ResponseEntity<SummonerTotalMasteryDTO> resp =
                        restTemplate.getForEntity(String.format("https://br1.api.riotgames.com/lol/champion-mastery/v4/scores/by-puuid/%s?api_key=RGAPI-e94bd0e4-3f2f-48e5-8fe9-4c143bf8e50c", puuid),
                                SummonerTotalMasteryDTO.class);

        return resp.getBody();
    }



    //https://br1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/FJu-fEUB1i23kXyZi2G_-wmos0p_Ii7Cg-3JW8cywIXNBkJujCExb1bqwnTvGAFJ-c5W_Ew-xGGiJA?api_key=RGAPI-e94bd0e4-3f2f-48e5-8fe9-4c143bf8e50c
}
