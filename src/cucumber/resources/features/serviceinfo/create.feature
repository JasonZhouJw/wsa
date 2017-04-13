Feature: create new Service information

  @ServiceInfoCreate
  Scenario: success
    Given navigate to create Service Info page
    When input wsdl "wsdl" and input name "name" on CreateServiceInfoPage, submit form
    Then display "Success" on CreateServiceInfoPage
    And click return button on create Service Info page
    And display wsdl "wsdl" in column "2" row "0" on Service Info index page

  @ServiceInfoCreate
  Scenario: create success and start to assemble
    Given navigate to create Service Info page
    When input wsdl "http://localhost:8080/services/hello?wsdl" and input name "hello" on CreateServiceInfoPage, click save and assemble
    Then display "Success" on CreateServiceInfoPage
