package fr.weeab.mangalist.webapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@ConfigurationProperties(prefix = "app.webapp")
public class AppWebappProperties {

    private final CorsConfiguration cors = new CorsConfiguration();

    public CorsConfiguration getCors() {
        return this.cors;
    }
}
