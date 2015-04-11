package com.theconnman

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

@Transactional
class SlackFeedbackService {

	def grailsApplication
	def springSecurityService

	boolean send(String feedback) {
		String user = springSecurityService?.currentUser ? springSecurityService.currentUser.username : (grails.util.Metadata.current.'app.name' + ' Feedback');
		def config = grailsApplication.mergedConfig.grails.plugin.slackfeedback;
		RestBuilder rest = new RestBuilder();
		try {
			def resp = rest.post(config.webhook) {
				json {
					text = feedback
					username = user
				}
			}
			return true;
		} catch (e) {
			return false;
		}
	}
}
