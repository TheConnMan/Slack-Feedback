import grails.util.Environment

def loc = ['../UserConfig.groovy', 'webapps/ROOT/Jenkins.groovy'].grep { new File(it).exists() }.first()
def localConfig = new ConfigSlurper(Environment.current.name).parse(new File(loc).toURI().toURL())

grails.app.context = '/'

log4j = {
	error 'org.codehaus.groovy.grails',
	      'org.springframework',
	      'org.hibernate',
	      'net.sf.ehcache.hibernate'
}

grails {
	plugin {
		slackfeedback {
			webhook = localConfig.slackfeedback.webhook
			token = localConfig.slackfeedback.token
			userDomainClassName = 'com.theconnman.feedback.User'
		}
	}
}
