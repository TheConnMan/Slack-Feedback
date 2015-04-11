package com.theconnman.feedback

import grails.converters.JSON

class SlackController {

    def submit() {
		render([success: true] as JSON)
	}
}
