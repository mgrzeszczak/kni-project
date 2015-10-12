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
@Constraint(validatedBy = AvailableValidator.class)
public @interface Available {
    enum Field {
        EMAIL
    }
    Field type();
    String mesage() default "Not available.";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
}
