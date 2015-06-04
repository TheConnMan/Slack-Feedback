Slack Feedback
=========

Chat box for quick app feedback sent to a Slack channel from a Grails project. Optionally the plugin can be configured to allow responses from Slack back to the application users.

![Feedback in Grails](https://raw.githubusercontent.com/TheConnMan/Slack-Feedback/dev/resources/SlackFeedback-Grails.png)
![Feedback in Slack](https://raw.githubusercontent.com/TheConnMan/Slack-Feedback/dev/resources/SlackFeedback-Slack.png)

## Requirements

- Slack account with Admin permissions
- jQuery
- Grails project with Spring Security installed
- Semantic UI
	- **NOTE:** Currently **Slack Feedback** only supports use with the **Semantic UI** frontend framework, additional instructions for use without **Semantic UI** will be a future improvement

## Feedback from Grails to Slack

This is the basic configuration of **Slack Feedback** and possible even if your application is hidden behind a firewall. User feedback can be posted from the Grails application out to Slack.

### Slack Requirements

- Create an [Incoming WebHooks integration](https://my.slack.com/services/new/incoming-webhook)
- Select a channel to post messages to
	- **NOTE:** This can be overwritten in the config. This is helpful if multiple projects use the same webhook.
- Add the WebHook url to the config as shown below

### Config

All config items contain the prefix **grails.plugin.slackfeedback.**

**NOTE:** Config items with * after them are secret information. It is highly reccommended to put this information in secret files and pull it into the Grails project at runtime.

- **webhook** - The Incoming WebHook URL provided by Slack*
- **channel** - The channel to post to [Optional]
- **userDomainClassName** - The full package name of the Spring Security User object (e.g. 'com.theconnman.feedback.User') [Required for Outgoing WebHooks]

### Chatbox Layout

**Slack Feedback** provides a single layout template which contains all necessary HTML and JavaScript. A basic use of the layout is below:

```
<g:render template="/layouts/chatbox" plugin="slack-feedback" />
```

To trigger the modals contined within the template call the JavaScript function `slackFeedback()`. A simple button which does this is shown below and can be placed anywhere on a page:

```
<div class="ui primary button" onclick="slackFeedback()">Feedback</div>
```

An example using the **Chatbox** layout and trigger in a Semantic UI menu can be found in the [Slack Feedback](https://github.com/TheConnMan/Slack-Feedback/blob/master/grails-app/views/layouts/semantic.gsp) plugin test project.

### Layout Parameters

The **chatbox** template takes multiple optional parameters which customize the modals. These are passed in `model` attribute as a map. An example is shown below:

```
<g:render template="/layouts/chatbox" plugin="slack-feedback" model="[title: 'Need help or have some feedback?', maxlength: 1000]" />
```

- **title** - The title of the form modal
	- **Default:** 'Send Us A Comment'

- **label** - The label of the textarea
	- **Default:** 'Feedback'

- **placeholder** - Placeholder text for the textarea
	- **Default:** ''

- **maxlength** - Maximum character length of the textarea
	- **Default:** 255

- **successText** - Success modal title
	- **Default:** 'Feedback Successfully Sent'

- **errorText** - Error modal title
	- **Default:** 'An Error Occured, Please Try Again Later'

## Feedback from Slack to Grails

It's one thing to have feedback come into Slack, it's another to be able to respond back to the app from within Slack. Below are instructions on how to configure Outgoing WebHooks for Slack and use them to communicate back to your Grails application.

### Slack Requirements

- Create an [Outgoing WebHooks integraion](https://my.slack.com/services/new/outgoing-webhook)
- Select a channel to post messages to
- Add a set of trigger words
- Add `[url]/slack/post` where `[url]` is the URL of your publicly visible Grails application

### Config

The same instructions from the above **Config** section apply.

- **token** - The Outgoing WebHook token provided by Slack* [Required for Outgoing WebHooks]

### SF TagLib

**Slack Feedback** also adds a taglib (the namespace is **sf**) which can be used to render user messages and message counts. The docs are included in the JavaDocs of the taglib. Examples of how to use the taglib tags can be found in the [example views](https://github.com/TheConnMan/Slack-Feedback/tree/master/grails-app/views/test) in the plugin.

For `<sf:eachMessage>` the body of the tag is executed for each message a user has. For a full list of fields for the Message object refer to the [Message domain object](https://github.com/TheConnMan/Slack-Feedback/blob/master/grails-app/domain/com/theconnman/feedback/Message.groovy). The use of `message.author` is encouraged instead of directly calling `message.respondent`.

### Slack Response Syntax

During the Slack Outgoing WebHooks integration setup a set of trigger words were set up. The following syntax must be used when sending a message through Slack on a channel set up with the Outgoing WebHook:

`[trigger] [username]: [message]`

The following is an example of the correct format:

`Message admin: Test message to admin using the trigger word Message`

## Development

Thank you for taking an interest furthering the development of **Slack Feedback**. Testing integration with Slack can be a bit difficult especially when a development machine needs to receive a POST request from Slack. To get around this issue I use the [Advanced REST client](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo) for Chrome to simulate Slack POSTs to the system. Outgoing WebHook integrations provide a sample POST request which I modified slightly to send through the REST client. For outgoing feedback I created a test channel in my personal Slack account to send data out to.