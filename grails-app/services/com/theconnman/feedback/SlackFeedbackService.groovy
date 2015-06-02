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
		def user = springSecurityService.currentUser;
		try {
			new Message(username: user.username, text: feedback[0..(Math.min(feedback.size(), 5000) - 1)]).save();
			return true;
		} catch (e) {
			return false;
		}
	}

	String receivePost(Map parameters) {
		Collection<String> textArray = (parameters.text - parameters.trigger_word).trim().split(':');
		if (textArray.size() == 1) {
			return 'No delimiter found, make sure to construct the message in the form [trigger word] [username]: [message]';
		} else {
			String username = textArray.first();
			def User = getUserDomain();
			def user = User.findByUsernameIlike(username);
			if (!user) {
				return 'No user by the name of *' + username + '* found'
			} else {
				String text = textArray.tail().join(':').trim();
				new Message(username: user.username, seen: false, respondent: parameters.user_name, text: text).save();
				return 'Message successfully sent';
			}
		}
	}

	def getUserDomain() {
		String className = grailsApplication.mergedConfig.grails.plugin.slackfeedback.userDomainClassName;
		return grailsApplication.getClassForName(className);
	}
}
