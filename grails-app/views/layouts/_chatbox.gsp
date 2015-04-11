<script src="${resource(dir: 'js', file: 'slack-feedback.js')}" charset="utf-8"></script>
<div class="ui modal chatbox">
	<i class="close icon"></i>
	<div class="header">${ title ?: 'Send Us A Comment' }</div>
	<div class="content">
		<div class="ui form">
			<div class="field">
				<label>${ label ?: 'Feedback' }</label>
				<g:textArea name="feedback" value="${ placeholder ?: '' }" maxlength="${ maxlength ?: 255 }" />
			</div>
		</div>
	</div>
	<div class="actions">
		<div class="ui button">Cancel</div>
		<div class="ui green button" onclick="submitSlackFeedback()">Send</div>
	</div>
</div>