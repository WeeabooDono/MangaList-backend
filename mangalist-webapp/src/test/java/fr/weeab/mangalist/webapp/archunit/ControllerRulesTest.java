package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class ControllerRulesTest {

    @ArchTest
    static final ArchRule controllerMustResideInAControllerPackage =
            classes().that().haveNameMatching(".*Controller").should().resideInAPackage("fr.weeab.mangalist.webapp.controller..")
            .as("Controllers should reside in a package named '..controller..'");
}
