package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import javax.persistence.Entity;
import java.io.Serializable;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class DomainRulesTest {

    @ArchTest
    static final ArchRule entitiesShouldResideInADomainPackage =
            classes().that().areAnnotatedWith(Entity.class)
                    .should().resideInAPackage("..domain..")
                    .as("Entities should reside in a package '..domain..'");

    @ArchTest
    static final ArchRule entitiesShouldImplementSerializable =
            classes().that().areAnnotatedWith(Entity.class)
                    .should().implement(Serializable.class)
                    .as("Entities should implement Serializable");
}
