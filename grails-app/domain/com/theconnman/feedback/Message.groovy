package com.theconnman.feedback

class Message {

	User user
	Date dateCreated
	String text

	static constraints = {
		user()
		dateCreated()
		text maxSize: 5000
	}
}
