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
	

