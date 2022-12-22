package com.capgemini.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Login")
public class Login {

	@Id
	private String userid;
	private String userName;
	private String password;
	private boolean loggedIn;
	private boolean isOwner;

}
