Feature: Create User

  Scenario:
    Given to createUser page
    When input username "Test" and input password  "password" twice
    Then show message "Success"