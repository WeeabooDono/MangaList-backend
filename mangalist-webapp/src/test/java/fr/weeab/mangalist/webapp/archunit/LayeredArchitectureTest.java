package fr.weeab.mangalist.webapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "fr.weeab.mangalist", importOptions = { ImportOption.DoNotIncludeTests.class })
public class LayeredArchitectureTest {

    @ArchTest
    static final ArchRule layerDependenciesAreRespected = layeredArchitecture()
            .layer("Controllers").definedBy("fr.weeab.mangalist.webapp.controller..")
            .layer("Services").definedBy("fr.weeab.mangalist.core.service..")
            .layer("Repositories").definedBy("fr.weeab.mangalist.core.repository..")
            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Services");
}
