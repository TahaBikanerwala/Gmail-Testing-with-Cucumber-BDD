Feature: Launch Gmail Webapp

Scenario: To verify that user is able to launch Gmail webapp via chrome browser
	Given Chrome browser and webdriver is installed
	Then user should be able to launch Gmail webapp
	
Scenario: Compose mail from Gmail
	Given user is logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And fill in Subject
	And fill in Body
	Then user should be able to send the email to the recipient
	
Scenario: Send mail with recipient in CC
    Given user is already logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And add cc recipient
	And fill in Subject
	And fill in Body
	Then user should be able to send the email to the recipient
	
Scenario: Send mail with emoji
	Given user is already logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And fill in Subject
	And fill in Body
	And click on Emoji icon
	And select an emoji
	Then user should be able to send the email to the recipient
	
Scenario: Send mail without Recipient
	Given user is logged in to the account
	When user click on Compose button
	And fill in Subject
	And fill in Body
	Then user should not be able to send the email to the recipient
	And error message should be displayed
	
Scenario: Send mail without Subject
	Given user is already logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And fill in Body
	Then user should be able to send the email to the recipient
	
Scenario: Send mail without Body Text
	Given user is already logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And fill in Subject
	Then user should be able to send the email to the recipient
	
Scenario: Send mail with Attachment from Google Drive
	Given user is already logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And fill in Subject
	And fill in Body
	And click on attachments
	And insert attachment
	Then user should be able to send the email to the recipient
	
	
Scenario: Compose mail and then discard the draft
	Given user is already logged in to the account
	When user click on Compose button
	And fill in Recipient Id
	And fill in Subject
	And fill in Body
	And click on delete icon on bottom left
	Then the draft should be deleted and user should not be able to see it in drafts or anywhere else
	
	

	
	

