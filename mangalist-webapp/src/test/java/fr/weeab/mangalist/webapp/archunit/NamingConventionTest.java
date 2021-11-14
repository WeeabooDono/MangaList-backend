package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class NamingConventionTest {

    @ArchTest
    static final ArchRule servicesShouldBeSuffixed =
            classes().that().resideInAPackage("..core.service.")
                    .should().haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule servicesImplementationShouldBeSuffixed =
            classes().that().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("ServiceImpl");


    @ArchTest
    static final ArchRule controllersShouldBeSuffixed =
            classes().that().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static final ArchRule dtosShouldBeSuffixed =
            classes().that().resideInAPackage("..dto..")
                .should().haveSimpleNameEndingWith("DTO");

}
