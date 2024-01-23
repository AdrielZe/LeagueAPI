package RiotAPI.Riot.services;

import RiotAPI.Riot.dtos.QuerySummonerDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

public class FindPuuidService {
    public String getPuuidFromApi(@PathVariable("summoner") String summoner ){
        RestTemplate restTemplate = new RestTemplate();

        QuerySummonerDTO querySummonerDTO = restTemplate.getForObject(String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=RGAPI-dfdfe0d2-8032-4891-a7ec-9bd2a75ceb69", summoner), QuerySummonerDTO.class);

        return querySummonerDTO.getPuuid();
    }
}
