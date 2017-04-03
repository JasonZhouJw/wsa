Feature: Update case Group

  Scenario: success
    Given navigate to update Case Group page
    When modify the name "Update"
    Then update Case group Page should display "Success"

  Scenario: return
    Given navigate to update Case Group page
    When click return button on update Case Group page
    Then back to Index page

  Scenario: fail to update, name conflict
    Given navigate to update Case group page
    When modify the name "Default"
    Then display error message ""