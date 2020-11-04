package ml.ovcorp.music.importer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spotify")
public class SpotifyClientProperties {

    private String clientId;
    private String clientSecret;
    private String redirectUri;

}
