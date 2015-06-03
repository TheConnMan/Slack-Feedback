package com.theconnman.feedback

class SlackFeedbackTagLib {

	static namespace = 'sf';

	def springSecurityService

	/**
	 * Renders the body for each message the user has.
	 *
	 * @attr var Message variable name (Default: 'it')
	 * @attr order Sort order (Default: 'asc')
	 * @attr max Maximum messages shown starting from the latest
	 * @attr offset Sorted message offset for use with 'max' (Default: 0)
	 * @attr see Updated all shown messages to 'seen' (Default: true)
	 */
	def eachMessage = { attrs, body ->
		def user = springSecurityService.currentUser;
		Collection<Message> messages = Message.findAllByUsername(user.username, [sort: 'dateCreated', order: 'desc', max: attrs.max, offset: attrs.offset ?: 0]);
		if (attrs.order != 'desc') {
			messages = messages.reverse();
		}
		messages.each { Message message ->
			if (attrs.see == null || attrs.see == 'true') {
				message.seen = true;
				message.save(flush: true);
			}
			out << body((attrs.var ?: 'it'): message)
		}
	}

	/**
	 * Number of unseen messages the current user has.
	 */
	def unseenCount = {
		def user = springSecurityService.currentUser;
		out << Message.countByUsernameAndSeen(user.username, false)
	}

	/**
	 * Renders content if the current user has any unseen messages.
	 */
	def ifHasUnseen = { attrs, body ->
		def user = springSecurityService.currentUser;
		if (Message.countByUsernameAndSeen(user.username, false) != 0) {
			out << body()
		}
	}

	/**
	 * Renders content if the current user has no unseen messages.
	 */
	def ifHasNoUnseen = { attrs, body ->
		def user = springSecurityService.currentUser;
		if (Message.countByUsernameAndSeen(user.username, false) == 0) {
			out << body()
		}
	}
}
