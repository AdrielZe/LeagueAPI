package RiotAPI.Riot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consulta-invocador")
public class RiotApiController {

    @GetMapping(value = "{summoner}")
    public QuerySummonerDTO querySummoner(@PathVariable("summoner") String summoner){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<QuerySummonerDTO> resp =
                restTemplate
                        .getForEntity(
                                String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=RGAPI-e94bd0e4-3f2f-48e5-8fe9-4c143bf8e50c", summoner),
                                QuerySummonerDTO.class);
        return resp.getBody();
    }
}
