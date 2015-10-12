package pl.kni.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Maciej on 12.10.2015.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniversityEmailValidator.class)
public @interface UniversityEmail {
    String domain();
    String mesage() default "Only university e-mail addresses accepted.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
