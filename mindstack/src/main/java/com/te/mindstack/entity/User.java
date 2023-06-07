package com.te.mindstack.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_Id")
	private int userId;
	@Column(name = "Name", length = 40, nullable = false)
	private String userName;
	@Column(name = "email", nullable = false)
	private String userEmail;
	@Column(name = "password", nullable = false)
	private String userPassword;
	@Column(name = "mobileNumber", length = 15, nullable = false)
	private long userMobileNumber;
	@Column(name = "age", nullable = false)
	private int userAge;
	@Column(name = "DOB", nullable = false)
	private String userDOB;
	@Column(name = "gender", nullable = false)
	private String userGender;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "user")
	private List<Product> products;

	@ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;

}
