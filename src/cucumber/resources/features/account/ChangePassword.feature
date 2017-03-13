Feature: change password

  Scenario: change success
    Given navigate to ChangePassword page with User ID "1"
    When  input password  "12345678" twice
    Then success

  Scenario: password is not same
    Given navigate to ChangePassword page with User ID "1"
    When input password "11111111" and input repeatPassword "22222222"
    Then update fail, display error message "Password is not same."