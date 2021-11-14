package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class ServiceRulesTest {

    @ArchTest
    static final ArchRule servicesShouldResideInAServicePackage =
            classes().that().haveNameMatching(".*Service")
                    .should().resideInAPackage("..service..")
                    .as("Services should reside in a package names '..service..'");


    @ArchTest
    static final ArchRule servicesImplementationShouldResideInAServicePackage =
            classes().that().haveNameMatching(".*ServiceImpl")
                    .should().resideInAPackage("..impl..")
                    .as("Services implementations should reside in a package names '..impl..'");
}
