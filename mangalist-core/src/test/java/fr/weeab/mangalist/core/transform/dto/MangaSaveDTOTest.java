package fr.weeab.mangalist.core.transform.dto;

import fr.weeab.mangalist.core.context.AbstractValidationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MangaSaveDTOTest extends AbstractValidationTest {

    @Autowired
    private Validator validator;

    private MangaSaveDTO dto;

    @BeforeEach
    void prepare() {
        this.dto = new MangaSaveDTO();

        dto.setId(1L);

        // Already tested
        GenreSaveDTO genre = new GenreSaveDTO();
        genre.setId(1L);
        genre.setName("name");

        dto.setTitle("title");
        dto.setAuthor("author");
        dto.setDescription("description");
        dto.setGenres(Set.of(genre));
    }

    private static Stream<Arguments> provideValuesForValidators() {
        return Stream.of(
                Arguments.of("id", null, NotNull.class)
        );
    }

    @ParameterizedTest(name = "with \"{0}\" value as \"{1}\" should trigger {2} Validation Constraints Exception")
    @MethodSource("provideValuesForValidators")
    void should_throw_validation_constraints(String propertyPath, Object value, Class<? extends Annotation> annotationType) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        setValueToParam(dto, propertyPath, value);

        var violations = validator.validate(dto);
        assertThat(violations).hasSize(1);

        var violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo(propertyPath);
        assertThat(violation.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(annotationType);
    }
}