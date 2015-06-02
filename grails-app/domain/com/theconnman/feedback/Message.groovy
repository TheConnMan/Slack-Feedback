package com.theconnman.feedback

class Message {

	User user
	Date dateCreated
	boolean seen = true
	String text

	static constraints = {
		user()
		dateCreated()
		seen()
		text maxSize: 5000
	}
}
