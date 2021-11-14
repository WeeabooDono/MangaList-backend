package fr.weeab.mangalist.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"fr.weeab.mangalist"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class}) // TODO: virer l'exclude quand on aura la sécurité
@EnableConfigurationProperties({LiquibaseProperties.class})
public interface BaseApplication {
}
