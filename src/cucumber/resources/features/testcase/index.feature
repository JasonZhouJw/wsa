Feature: Index page

  Scenario: show index page
    Given navigate to TestCase Index page
    Then Index page should display "There is no data yet"

  Scenario: search test case
    Given navigate to TestCase Index page
    When input name "admin" and select "Active"
    Then Index page should display "There is no data yet"
