import com.theconnman.feedback.User;

class BootStrap {

	def init = { servletContext ->
		new User(username: 'Test User').save();
	}
}