package ml.ovcorp.music.importer.controller;

import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import lombok.RequiredArgsConstructor;
import ml.ovcorp.music.importer.service.SpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UtilController {

    private final SpotifyService spotifyService;

    @GetMapping("callback")
    public Object getCallbackResult(@RequestParam(name = "code", required = false) String code) {
        return getTokenByCode(code);
    }

    private AuthorizationCodeCredentials getTokenByCode(String code) {
        return spotifyService.fromCodeToToken(code);
    }
}
