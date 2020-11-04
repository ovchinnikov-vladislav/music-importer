package ml.ovcorp.music.importer.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ml.ovcorp.music.importer.config.SpotifyClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class SpotifyService {

    private final SpotifyApi spotifyApi;

    public SpotifyService(SpotifyClientProperties properties) {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(properties.getClientId())
                .setClientSecret(properties.getClientSecret())
                .setRedirectUri(SpotifyHttpManager.makeUri(properties.getRedirectUri()))
                .build();
    }

    public String getPrepareRedirectUri() {
        String scope = "ugc-image-upload user-read-recently-played playlist-modify-private streaming user-read-email user-follow-read user-follow-modify user-library-modify user-read-currently-playing playlist-read-collaborative playlist-read-private user-read-private user-read-playback-state user-read-playback-position playlist-modify-public user-top-read app-remote-control user-library-read user-modify-playback-state";
        AuthorizationCodeUriRequest request = spotifyApi.authorizationCodeUri()
                .scope(scope)
                .build();
        URI uri = request.execute();

        return uri.toString();
    }

    public AuthorizationCodeCredentials fromCodeToToken(String code) {

        try {
            return spotifyApi.authorizationCode(code).build().execute();
        } catch (Exception exc) {
            log.error(exc.getMessage(), exc);
            throw new RuntimeException(exc.getMessage());
        }
    }

    public Object getLikeTracks(String accessToken) {

        try {
            spotifyApi.setAccessToken(accessToken);
            return spotifyApi.getUsersSavedTracks().build().execute().getItems();
        } catch (Exception exc) {
            log.error(exc.getMessage(), exc);
            throw new RuntimeException(exc.getMessage());
        }

    }

    public User getUserInfo(String accessToken) {

        try {
            spotifyApi.setAccessToken(accessToken);
            return spotifyApi.getCurrentUsersProfile().build().execute();
        } catch (Exception exc) {
            log.error(exc.getMessage(), exc);
            throw new RuntimeException(exc.getMessage());
        }

    }
}
