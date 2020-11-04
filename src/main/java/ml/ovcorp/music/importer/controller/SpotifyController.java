package ml.ovcorp.music.importer.controller;

import com.wrapper.spotify.model_objects.specification.User;
import lombok.RequiredArgsConstructor;
import ml.ovcorp.music.importer.service.SpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify")
@RequiredArgsConstructor
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/user_info")
    public User getUserInfo(@RequestParam("access_token") String accessToken) {
        return spotifyService.getUserInfo(accessToken);
    }

    @GetMapping("/like_tracks")
    public Object getLikesTracks(@RequestParam("access_token") String accessToken) {
        return spotifyService.getLikeTracks(accessToken);
    }

}
