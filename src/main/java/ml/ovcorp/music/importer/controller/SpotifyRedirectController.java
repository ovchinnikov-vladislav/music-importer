package ml.ovcorp.music.importer.controller;

import lombok.RequiredArgsConstructor;
import ml.ovcorp.music.importer.service.SpotifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class SpotifyRedirectController {

    private final SpotifyService spotifyService;

    @GetMapping(value = "/spotify/auth")
    public String getAuth() {
        return String.format("redirect:%s", spotifyService.getPrepareRedirectUri());
    }

}
