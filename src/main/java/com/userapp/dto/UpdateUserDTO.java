package com.userapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	@Email(message = "Email should be valid", regexp = "^[A-Za-z0-9+_.-]+@example.com") // Email Validation with Regex
	private String email;

	@Min(value = 18, message = "Age should be greater than 18") // @Min : value should not be less than what is defined
	@Max(value = 100, message = "Age should be less than 100") // @Max: value should not be greater than what is defined
	private Integer age;

}
