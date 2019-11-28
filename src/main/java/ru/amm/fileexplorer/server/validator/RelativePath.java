package ru.amm.fileexplorer.server.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RelativePathValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RelativePath {
    String message() default "invalid path";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
