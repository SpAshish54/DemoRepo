package com.learnings.capstone.business;


import com.learnings.capstone.entity.Users;

import lombok.Data;

@Data
public class LoggedInUser {
    private Users loggedInUser;

	public Users getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Users loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	}
