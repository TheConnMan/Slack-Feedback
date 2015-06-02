package com.theconnman.feedback

class SlackFeedbackTagLib {
	
	static namespace = 'sf';

	def springSecurityService

	def eachMessage = { attrs, body ->
		User user = springSecurityService.currentUser;
		Message.findAllByUser(user, [sort: 'dateCreated', order: attrs.order ?: 'asc', max: attrs.max, offset: attrs.offset ?: 0]).each { Message message ->
			out << body((attrs.var ?: 'it'): message)
		}
	}
}
