grails.project.work.dir = 'target'

grails.project.dependency.resolver = 'maven'
grails.project.dependency.resolution = {
	inherits 'global'
	log 'warn'
	repositories {
		mavenLocal()
		grailsCentral()
		mavenCentral()
	}

	plugins {
		build(':release:3.1.1', ':tomcat:7.0.50.1') {
			export = false
		}
		compile(':hibernate:3.6.10.17') {
			export = false
		}
		compile ':plugin-config:0.2.0'
		compile ':rest-client-builder:2.1.1'
	}
}
