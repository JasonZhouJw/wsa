Feature: Create case Group

  @CreateCaseGroup
  Scenario: success
    Given navigate to createCaseGroup page
    When input case group name "temp"
    Then create case page display "Success"

  @CreateCaseGroup
  Scenario:fail, conflict name
    Given navigate to createCaseGroup page
    When input existing case group name
    Then create case page display "The data is exist."

  Scenario: return
    Given navigate to createCaseGroup page
    When click return button on create case group page
    Then back to case group Index page