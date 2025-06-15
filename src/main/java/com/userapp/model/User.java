package com.userapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user") // maps with the table in the database
@ToString
public class User {
	@Id // Identifies the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Identifies the generation strategy
	private int id;

	@Column(name = "fname") // maps with the column in the table
	private String firstName;

	@Column(name = "lname")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private int age;

	@Transient // not mapped to any column in the database
	private String fullName;

	public User(String firstName, String lastName, String email, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

	// `fullName` is not stored in DB but computed dynamically
	public String getFullName() {
		return firstName + " " + lastName;
	}

	public void setFullName(String fullName) {
		// Optional: allow setting it manually if needed
		this.fullName = fullName;
	}

}
