package top.bujiaban.test.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "top.bujiaban.test")
public class LayerDependenciesRules {

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .layer("interfaces").definedBy("..interfaces..")
            .layer("application").definedBy("..application..")
            .layer("domain").definedBy("..domain..")
            .layer("infrastructure").definedBy("..infrastructure..")

            .whereLayer("interfaces").mayNotBeAccessedByAnyLayer()
            .whereLayer("application").mayOnlyBeAccessedByLayers("interfaces")
            .whereLayer("domain").mayOnlyBeAccessedByLayers("application", "infrastructure")
            .whereLayer("infrastructure").mayNotBeAccessedByAnyLayer();

}
