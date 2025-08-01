package com.example.seating_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
	public class User {
	    @Id

	    @Column(unique = true)
	    private String username;

	    private String password;



		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public User() {}

		public User( String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		@Override
		public String toString() {
			return "User " + ", username=" + username + ", password=" + password + "]";
		}

	    // Getters and setters
		
		
	    
	


}
