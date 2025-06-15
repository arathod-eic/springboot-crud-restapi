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
public class UserDTO {

	private Integer id;

	@NotBlank(message = "First Name cannot be blanked") // @NotBlank: Ensures field value is not blank
	private String firstName;

	@NotBlank(message = "First Name cannot be blanked")
	private String lastName;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email should be valid", regexp = "^[A-Za-z0-9+_.-]+@example.com") // Email Validation with Regex
	private String email;

	@Min(value = 18, message = "Age should be greater than 18") // @Min : value should not be less than what is defined
	@Max(value = 100, message = "Age should be less than 100") // @Max: value should not be greater than what is defined
	private Integer age;

}
