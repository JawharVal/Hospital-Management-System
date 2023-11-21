package org.example.DI;

import java.lang.annotation.*;

@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}