package RiotAPI.Riot.services;

import RiotAPI.Riot.dtos.MasteryListDTO;
import RiotAPI.Riot.dtos.QuerySummonerDTO;
import RiotAPI.Riot.dtos.SummonerMasteryChampionsDTO;
import RiotAPI.Riot.dtos.SummonerTotalMasteryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.management.Query;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class RiotApiService {

    public ResponseEntity<String> querySummoner(String summoner){
        RestTemplate restTemplate = new RestTemplate();

        QuerySummonerDTO querySummonerDTO = restTemplate.getForObject(String.format("https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=RGAPI-dfdfe0d2-8032-4891-a7ec-9bd2a75ceb69", summoner), QuerySummonerDTO.class);
        String summonerInfos = "O nome de do invocador é: " + querySummonerDTO.getName() + "\nO PUUID do invocador é: " + querySummonerDTO.getPuuid() + "\nO nível do invocador é: " + querySummonerDTO.getSummonerLevel();


        return ResponseEntity.ok(summonerInfos);
    };
    public ResponseEntity<String> summonerMasteryChampions(String summoner){
        RestTemplate restTemplate = new RestTemplate();
        FindPuuidService findPuuidService = new FindPuuidService();
        String puuid = findPuuidService.getPuuidFromApi(summoner);
        FindChampionNameService findChampionNameService = new FindChampionNameService();
        CalculateLastDatePlayedService calculateLastDatePlayedService = new CalculateLastDatePlayedService();

        ResponseEntity<List<SummonerMasteryChampionsDTO>> resp = restTemplate.exchange( String.format("https://br1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/%s?api_key=RGAPI-dfdfe0d2-8032-4891-a7ec-9bd2a75ceb69", puuid),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        List<SummonerMasteryChampionsDTO> masteryChampionsList = resp.getBody();

        List<MasteryListDTO> masteryListDTOList = new ArrayList<>();
        for(SummonerMasteryChampionsDTO masteryChampion : masteryChampionsList) {
            MasteryListDTO masteryListDTO = new MasteryListDTO();
            masteryListDTO.setChampionId(masteryChampion.getChampionId());
            masteryListDTO.setMasteryLevel(masteryChampion.getChampionLevel());
            masteryListDTO.setChampionName(findChampionNameService.findChampionName(masteryChampion.getChampionId()));
            masteryListDTO.setLastDatePlayed(calculateLastDatePlayedService.calculateLastDatePlayed(masteryChampion.getLastPlayTime()));
            masteryListDTOList.add(masteryListDTO);
        }

        String masteryResponse = "Maestrias de " + summoner + "\n";

        for(MasteryListDTO mastery : masteryListDTOList){
            masteryResponse +=
                    "--------------------\n" +
                            "O ID do campeão é: " + mastery.getChampionId() +
                            "\nO nome do campeão é: " + mastery.getChampionName() +
                            "\nO nível de maestria com o campeão é: " + mastery.getMasteryLevel() +
                            "\nA última vez em que o invocador jogou com ele: " + mastery.getLastDatePlayed() + "\n" +
                    "--------------------\n";
        }

        return  ResponseEntity.ok(masteryResponse);

    }


    public ResponseEntity<String> summonerTotalMastery(String summoner) {
        RestTemplate restTemplate = new RestTemplate();
        FindPuuidService findPuuidService = new FindPuuidService();

        String puuid = findPuuidService.getPuuidFromApi(summoner);

        ResponseEntity<SummonerTotalMasteryDTO> resp =
                restTemplate.getForEntity(String.format("https://br1.api.riotgames.com/lol/champion-mastery/v4/scores/by-puuid/%s?api_key=RGAPI-dfdfe0d2-8032-4891-a7ec-9bd2a75ceb69", puuid),
                        SummonerTotalMasteryDTO.class);

        String totalMastery = "A soma de todos os níveis de maestria é de: " + resp.getBody().getTotalMastery();

        return ResponseEntity.ok(totalMastery);
    }
}
