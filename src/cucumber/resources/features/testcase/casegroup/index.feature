Feature: Case Group Index

  Scenario: display no data
    Given navigate to Case Group Index page
    Then display no data message in Case Group Index Page

  @CaseGroupIndex
  Scenario: display index
    Given navigate to Case Group Index page
    Then should display test case group in row "0" and column "1"
