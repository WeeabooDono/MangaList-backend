package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class LayeredDependencyRulesTests {

    @ArchTest
    static final ArchRule repositoriesShouldNotDependOnServices =
            noClasses().that().resideInAPackage("..core.repository..")
                    .should().dependOnClassesThat().resideInAPackage("..service..");

    @ArchTest
    static final ArchRule repositoriesShouldNotAccessServices =
            noClasses().that().resideInAPackage("..core.repository..")
                    .should().accessClassesThat().resideInAPackage("..service..");

    @ArchTest
    static final ArchRule servicesShouldNotDependOnControllers =
            noClasses().that().resideInAPackage("..core.service..")
                    .should().dependOnClassesThat().resideInAPackage("..controller..");

    @ArchTest
    static final ArchRule servicesShouldNotAccessControllers =
            noClasses().that().resideInAPackage("..core.service..")
                    .should().accessClassesThat().resideInAPackage("..controller..");

    @ArchTest
    static final ArchRule controllersShouldNotAccessRepositories =
            noClasses().that().resideInAPackage("..webapp.controller..")
                    .should().accessClassesThat().resideInAPackage("..core.repository..");

    @ArchTest
    static final ArchRule servicesShouldOnlyBeAccessedByCertainPackages =
            classes().that().resideInAPackage("..core.service..")
                    .should().onlyBeAccessed().byAnyPackage("..constraint..", "..service..", "..webapp.controller..");
}
