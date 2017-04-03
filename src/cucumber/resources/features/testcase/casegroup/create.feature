Feature: Create case Group

  Scenario: success
    Given navigate to createCaseGroup page
    When input name "temp"
    Then createCasePage display "Success"

  Scenario: return
    Given navigate to createCaseGroup page
    When click return button
    Then back to Index page