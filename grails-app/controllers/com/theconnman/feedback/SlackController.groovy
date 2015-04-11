package com.theconnman.feedback

import grails.converters.JSON

class SlackController {

	def slackFeedbackService

    def submit() {
		render([success: slackFeedbackService.send(params.feedback)] as JSON)
	}
}
