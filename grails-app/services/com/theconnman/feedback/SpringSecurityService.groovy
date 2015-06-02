package com.theconnman.feedback

import grails.transaction.Transactional

@Transactional
class SpringSecurityService {

	User getCurrentUser() {
		return User.get(1);
	}
}
