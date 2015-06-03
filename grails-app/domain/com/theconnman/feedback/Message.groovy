package com.theconnman.feedback

class Message {

	String username
	Date dateCreated
	boolean seen = true
	String respondent
	String text

	static constraints = {
		username()
		dateCreated()
		seen()
		respondent nullable: true
		text maxSize: 5000
	}

	String getAuthor() {
		return respondent ?: username
	}
}
