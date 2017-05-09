#language: en
Feature: Open the dashboard view
 
  Scenario: Open view and receive message
    Given a list of voters:
    | name | vote | proposal | comment |
    | "Alvaro" | "like" | "cucumber" | "jirafa" | 
    | "Pablo" | "dislike" | "cucumber" | "gorila" | 
    Then the webpage contains that list:
     | name | vote | proposal | comment |
    | "Alvaro" | "like" | "cucumber" | "jirafa" | 
    | "Pablo" | "dislike" | "cucumber" | "gorila" | 