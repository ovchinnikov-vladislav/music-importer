package ml.ovcorp.music.importer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/yandex_music")
@RequiredArgsConstructor
public class YandexMusicController {

    @GetMapping("/user-likes-tracks")
    public Object getLikesTracks(@RequestParam("username") String username, @RequestParam("password") String password) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return restTemplate.postForObject("http://localhost:5000/user-likes-tracks", map, Object.class);
    }

}
