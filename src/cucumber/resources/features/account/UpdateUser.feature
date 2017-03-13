Feature: update user

  Scenario: update success
    Given navigate to Update user page with User ID "1"
    When  input password  "1234" twice
    Then display "success"