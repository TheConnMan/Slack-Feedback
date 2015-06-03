<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="semantic"/>
		<title>Slack Feedback</title>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timeago/1.4.1/jquery.timeago.min.js" charset="utf-8"></script>
	</head>
	<body>
		<div class="ui segment">
			<h1 class="ui header">Messages</h1>
			Unseen Count: <sf:unseenCount />
			<div class="ui feed">
				<sf:eachMessage var="message">
					<div class="event">
						<div class="content">
							<div class="summary">
								${ message.author } <abbr class="timeago date" title="${ message.dateCreated }"></abbr>
							</div>
							<div class="extra text">
								${ message.text }
							</div>
						</div>
					</div>
				</sf:eachMessage>
			</div>
		</div>
		<script>
			$(function() {
				$('.ui.dropdown').dropdown({
					on: 'hover'
				});
				$('.timeago').timeago();
			});
		</script>
	</body>
</html>