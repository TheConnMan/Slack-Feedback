<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="semantic"/>
		<title>Slack Feedback</title>
	</head>
	<body>
		<div class="ui segment">
			<h1>Slack Feedback Plugin</h1>
			<g:render template="/layouts/chatbox" model="[title: 'Need help or have some feedback?']" />
			<div class="ui primary button" onclick="slackfeedback()">Feedback</div>
		</div>
	</body>
</html>