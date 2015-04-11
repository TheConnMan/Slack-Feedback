function slackFeedback() {
	$('.modal.chatbox-feedback').modal('show');
}

function submitSlackFeedback() {
	var feedback = $('.modal.chatbox-feedback #feedback').val();
	if (feedback.length > 0) {
		$.ajax({
			url: '/slack/submit',
			data: {
				feedback: feedback
			},
			success: function(data) {
				if (data.success) {
					$('.modal.chatbox-success').modal('show');
				} else {
					$('.modal.chatbox-error').modal('show');
				}
			},
			error: function() {
				$('.modal.chatbox-error').modal('show');
			}
		});
	}
}