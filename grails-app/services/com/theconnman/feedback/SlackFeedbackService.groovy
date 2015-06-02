package com.theconnman.feedback

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class SlackFeedbackService {

	def grailsApplication
	def springSecurityService

	boolean submit(String feedback) {
		return send(feedback) && save(feedback);
	}

	boolean send(String feedback) {
		String user = springSecurityService.currentUser.username;
		def config = grailsApplication.mergedConfig.grails.plugin.slackfeedback;
		RestBuilder rest = new RestBuilder();
		try {
			def resp = rest.post(config.webhook) {
				json {
					text = feedback
					username = user
					channel = config.channel
				}
			}
			return true;
		} catch (e) {
			return false;
		}
	}

	boolean save(String feedback) {
		User user = springSecurityService.currentUser;
		try {
			new Message(user: user, text: feedback[0..(Math.min(feedback.size(), 5000) - 1)]).save();
			return true;
		} catch (e) {
			return false;
		}
	}
}
