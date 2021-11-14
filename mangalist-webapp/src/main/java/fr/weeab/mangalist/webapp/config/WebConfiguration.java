package fr.weeab.mangalist.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfiguration {

    private final AppWebappProperties appProperties;

    public WebConfiguration(AppWebappProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = appProperties.getCors();

        if(!CollectionUtils.isEmpty(corsConfiguration.getAllowedOrigins())) {
            source.registerCorsConfiguration("/api/**", corsConfiguration);
        }

        return new CorsFilter(source);
    }
}
