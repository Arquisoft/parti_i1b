Feature: Proposals
Scenario: Add proposals
Given I have open the browser
Given I'm logged as a Citizen
When I add a proposal
Then There is a new proposal