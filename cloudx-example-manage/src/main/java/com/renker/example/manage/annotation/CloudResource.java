package com.renker.example.manage.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface CloudResource {
	
	@AliasFor("serverName")
	String value() default "";
	
	@AliasFor("value")
	String serverName() default "";
	
	String version() default "";
	
}
