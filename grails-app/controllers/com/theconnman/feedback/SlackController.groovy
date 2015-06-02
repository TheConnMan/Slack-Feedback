package com.theconnman.feedback

import grails.converters.JSON

class SlackController {

	def grailsApplication
	def slackFeedbackService

    def submit() {
		render([success: slackFeedbackService.submit(params.feedback)] as JSON)
	}

	def post() {
		if (params.token == grailsApplication.mergedConfig.grails.plugin.slackfeedback.token) {
			render([text: slackFeedbackService.receivePost(params)] as JSON)
		} else {
			render(status: 404)
		}
	}
}
