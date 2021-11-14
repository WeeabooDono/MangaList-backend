package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class RepositoryRulesTest {

    @ArchTest
    static final ArchRule repositoriesShouldResideInARepositoryPackage =
            classes().that().haveNameMatching(".*Repository")
                    .should().resideInAPackage("..repository..")
                    .as("Repositories should reside in a package names '..repository..'");

    @ArchTest
    static final ArchRule repositoriesShouldBeAnnotatedWithRepositoryAnnotation =
            classes().that().haveNameMatching(".*Repository")
                    .and().resideOutsideOfPackage("..repository.custom..").should().beAnnotatedWith(Repository.class)
                    .as("Repositories should be annotated with @Repository");
}
