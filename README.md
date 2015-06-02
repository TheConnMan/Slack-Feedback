Slack Feedback
=========

Chat box for quick app feedback sent to a Slack channel from a Grails project. Optionally the plugin can be configured to allow responses from Slack back to the application users.

## Requirements

- Slack account with Incoming WebHooks integration
- jQuery
- Grails project with Spring Security installed
- Semantic UI
	- *Currently **Slack Feedback** only supports use with the **Semantic UI** frontend framework*

### Optional

- Slack account with Outgoing WebHooks integration

## Setup

### Slack

#### Required

- Create an [Incoming WebHooks integration](https://my.slack.com/services/new/incoming-webhook)
- Select a channel to post messages to
	- **NOTE:** This can be overwritten in the config. This is helpful if multiple projects use the same webhook.
- Add the WebHook url to the config as shown below

#### Optional

- Create an [Outgoing WebHooks integraion](https://my.slack.com/services/new/outgoing-webhook)
- Select a channel to post messages to
- Add a set of trigger words
- Add `[url]/slack/post` where `[url]` is the URL of your publicly visible Grails application

### Config

All config items contain the prefix **grails.plugin.slackfeedback.**

**NOTE:** Config items with * after them are secret information. It is highly reccommended to put this information in secret files and pull it into the Grails project at runtime.

- **webhook** - The Incoming WebHook URL provided by Slack*
- **channel** - The channel to post to [Optional]
- **token** - The Outgoing WebHook token provided by Slack* [Required for Outgoing WebHooks]
- **userDomainClassName** - The full package name of the Spring Security User object (e.g. 'com.theconnman.feedback.User') [Required for Outgoing WebHooks]

## Use

**Slack Feedback** provides a single layout template which contains all necessary HTML and JavaScript. A basic use of the layout is below:

```
<g:render template="/layouts/chatbox" plugin="slack-feedback" />
```

To trigger the modals contined within the template call the JavaScript function `slackFeedback()`. A simple button which does this is shown below and can be placed anywhere on a page:

```
<div class="ui primary button" onclick="slackFeedback()">Feedback</div>
```

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

### Slack Responses

It's on thing to have feedback come into Slack, it's another to be able to respond back to the app from within Slack.

### TagLib

**Slack Feedback** also adds a taglib (the namespace is **sf**) which can be used to render user messages and message counts. The docs are included in the JavaDocs of the taglib.

### Response Syntax

During the Slack Outgoing WebHooks integration setup a set of trigger words were set up. The following syntax must be used when sending a message through Slack on a channel set up with the Outgoing WebHook:

`[trigger] [username]: [message]`

The following is an example of the correct format:

`Message admin: Test message to admin using the trigger word Message`