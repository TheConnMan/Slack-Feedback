<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Slack Feedback"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js" charset="utf-8"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.6/semantic.min.js" charset="utf-8"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/1.11.6/semantic.min.css" type="text/css">
		<g:layoutHead/>	
		<style>
			.content, .ui.menu {
				max-width: 800px;
				margin-left: auto;
				margin-right: auto;
			}
		</style>
	</head>
	<body>
		<div class="ui segment">
			<h1 class="ui header">Slack Feedback</h1>
		</div>
		<div class="ui menu">
			<div class="ui dropdown item">
				<i class="icon dropdown"></i> Test
				<div class="menu">
					<g:link class="item" controller="test">Test Page</g:link>
					<a class="item" onclick="sendFeedback()">Send Feedback</a>
				</div>
			</div>
		</div>
		<g:render template="/layouts/chatbox" model="[title: 'Need help or have some feedback?']" />
		<div class="content">
			<g:layoutBody/>
		</div>
		<div class="content">
			<div class="ui segment" style="margin-bottom: 15px;">
				<b>Slack Feedback ${ applicationContext.getBean('pluginManager').getGrailsPlugin('slack-feedback').version }</b>
			</div>
		</div>
	</body>
</html>