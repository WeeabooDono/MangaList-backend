package fr.weeab.mangalist.core.context;

import fr.weeab.mangalist.core.BaseApplication;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Import(value = BaseApplication.class)
public class ApplicationTest {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:dbchangelog-test.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(true);
        liquibase.setShouldRun(true);

        return liquibase;
    }
}
