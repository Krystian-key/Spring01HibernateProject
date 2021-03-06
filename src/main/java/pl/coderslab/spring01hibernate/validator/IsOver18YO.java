package pl.coderslab.spring01hibernate.validator;

import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsOver18YOValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsOver18YO {
    String message() default "{IsOver18YO.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

