package fr.weeab.mangalist.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.core")
public class AppCoreProperties {

    private final InfoSettings info = new InfoSettings();

    public static class InfoSettings {

        private String name;

        private String env;

        private String version;

        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnv() {
            return env;
        }

        public void setEnv(String env) {
            this.env = env;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
