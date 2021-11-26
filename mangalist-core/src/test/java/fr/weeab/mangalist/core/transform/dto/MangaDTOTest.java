package fr.weeab.mangalist.core.transform.dto;

import fr.weeab.mangalist.core.context.AbstractValidationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MangaDTOTest extends AbstractValidationTest {

    @Autowired
    private Validator validator;

    private MangaDTO dto;

    @BeforeEach
    void prepare() {
        this.dto = new MangaDTO();

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
                Arguments.of("title", null, NotNull.class),
                Arguments.of("author", null, NotNull.class),
                Arguments.of("description", null, NotNull.class),
                Arguments.of("genres", null, NotNull.class),
                Arguments.of("genres", Set.of(), Size.class)
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

    @Test
    void should_verify_that_nested_constraints_are_triggered() {
        // We just need to verity one constraint
        GenreSaveDTO genre = new GenreSaveDTO();
        genre.setName("name");
        dto.setGenres(Set.of(genre));

        var violations = validator.validate(dto);
        assertThat(violations).hasSize(1);

        var violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("genres[].id");
        assertThat(violation.getConstraintDescriptor().getAnnotation().annotationType()).isEqualTo(NotNull.class);
    }
}