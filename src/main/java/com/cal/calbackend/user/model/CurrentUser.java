package com.cal.calbackend.user.model;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CurrentUser {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
