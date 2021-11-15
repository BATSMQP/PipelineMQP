package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class AuthUser{
	public final String authUserId;
	public String firstName;
	public String lastName;
	public String username;
	public String password;
	public String email;
	
	public String getAuthUserId() {return authUserId;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getUsername() {return username;}
	public String getPassword() {return password;}
	public String getEmail() {return email;}
	
	public AuthUser(String authUserId, String firstName, String lastName, String username, String password, String email) {
		this.authUserId = authUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public AuthUser() {
		this.authUserId = null;
		this.firstName = null;
		this.lastName = null;
		this.username = null;
		this.password = null;
		this.email = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof AuthUser) {
			AuthUser other = (AuthUser) o;
			return authUserId.equals(other.authUserId);
		}
		
		return false;  // not a Constant
	}
	
	public String toString() {
		return "AuthUser " + authUserId + " is: " + firstName + " " + lastName + ".";
	}
	
}
