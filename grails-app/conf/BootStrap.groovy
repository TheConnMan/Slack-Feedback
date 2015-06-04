import com.theconnman.feedback.User

class BootStrap {

	def init = {
		new User(username: 'Test User').save()
	}
}
