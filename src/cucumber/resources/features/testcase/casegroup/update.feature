@CaseGroupUpdate
Feature: Update case Group

  Scenario: success
    Given navigate to update Case Group page
    When modify the case group name "Update"
    Then update Case group Page should display "Success"

  Scenario: return
    Given navigate to update Case Group page
    When click return button on update Case Group page
    Then back to case group Index page

  Scenario: fail to update, name conflict
    Given navigate to update Case Group page
    When modify the case group name "existing"
    Then update Case group Page should display "The data is exist."