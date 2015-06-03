class SlackFeedbackGrailsPlugin {
	def version = "2.1.0"
	def grailsVersion = "2.3 > *"
	def pluginExcludes = [
		"grails-app/views/test/*",
		"grails-app/views/layouts/semantic.gsp",
		"grails-app/domain/com/theconnman/feedback/User.groovy",
		"grails-app/controllers/com/theconnman/feedback/TestController.groovy",
		"grails-app/services/com/theconnman/feedback/SpringSecurityService.groovy"
	]
	def title = "Slack Feedback Plugin"
	def author = "TheConnMan"
	def authorEmail = "brian@theconnman.com"
	def description = 'Chat box for quick app feedback sent to a Slack channel from a Grails project. Optionally the plugin can be configured to allow responses from Slack back to the application users.'
	def documentation = "https://github.com/TheConnMan/Slack-Feedback"
	def license = "MIT"
	def issueManagement = [ url: "https://github.com/TheConnMan/Slack-Feedback/issues" ]
	def scm = [ url: "https://github.com/TheConnMan/Slack-Feedback" ]
}
