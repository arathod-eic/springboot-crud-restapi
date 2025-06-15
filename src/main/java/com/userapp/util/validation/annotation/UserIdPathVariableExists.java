package com.userapp.util.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.userapp.util.validator.UserIdPathVarialbeExistsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented // Makes this annotation appear in the JavaDocs of the target class or method
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER }) // Specifies where this annotation can be
																			// used: on fields, methods, and method
																			// parameters
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime, so Spring's validation system
									// can use it
@Constraint(validatedBy = UserIdPathVarialbeExistsValidator.class) // Connects the annotation to the validation logic
																	// class, here:
																	// UserIdPathVariableExistsValidator.class
public @interface UserIdPathVariableExists {
	String message() default "Entity with the given id not found in DB!"; //Default validation failure message

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
