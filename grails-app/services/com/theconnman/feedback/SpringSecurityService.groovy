package com.theconnman.feedback

import grails.transaction.Transactional

@Transactional
class SpringSecurityService {

	def getPrincipal() { [username: User.get(1).username] }
}
