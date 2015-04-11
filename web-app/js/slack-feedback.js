function slackFeedback() {
	$('.modal.chatbox').modal('show');
}

function submitSlackFeedback() {
	var feedback = $('.modal.chatbox #feedback').val();
	if (feedback.length > 0) {
		$.ajax({
			url: '/slack/submit',
			data: {
				feedback: feedback
			},
			success: function(data) {
				console.log(data)
			}
		});
	}
}