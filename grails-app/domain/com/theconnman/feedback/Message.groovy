package com.theconnman.feedback

class Message {

	User user
	Date dateCreated
	boolean seen = true
	String respondent
	String text

	static constraints = {
		user()
		dateCreated()
		seen()
		respondent nullable: true
		text maxSize: 5000
	}

	String getAuthor() {
		return respondent ?: user.username;
	}
}
