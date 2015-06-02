package com.theconnman.feedback

class SlackFeedbackTagLib {

	static namespace = 'sf';

	def springSecurityService

	/**
	 * Renders the body for each message the user has.
	 *
	 * @attr var Message variable name (Default: 'it')
	 * @attr order Sort order (Default: 'asc')
	 * @attr max Maximum messages shown
	 * @attr offset Sorted message offset for use with 'max' (Default: 0)
	 * @attr see Updated all shown messages to 'seen' (Default: true)
	 */
	def eachMessage = { attrs, body ->
		User user = springSecurityService.currentUser;
		Message.findAllByUser(user, [sort: 'dateCreated', order: attrs.order ?: 'asc', max: attrs.max, offset: attrs.offset ?: 0]).each { Message message ->
			if (attrs.see == null || attrs.see == 'true') {
				message.seen = true;
				message.save(flush: true);
			}
			out << body((attrs.var ?: 'it'): message)
		}
	}
	
	/**
	 * Number of unseen messages.
	 */
	def unseenCount = {
		User user = springSecurityService.currentUser;
		out << Message.countByUserAndSeen(user, false)
	}
}
