Feature: RussianDoll testing


  Scenario Outline: Russian Doll 1 - Create customer request
    Given I have extracted russian doll base firstname
    And I use customer header for doll
    And I have the data to create Russian Doll with "<lastName>","<phone>","<address1>","<address2>"
    When I create post request to create russian doll one
    Then I get status code 201 from doll
    And response body Russian Doll one should contain
      | firstName |
      | lastName  |
      | phone     |
      | addresses |
      | id        |

    Examples:
  | lastName | phone | address1 | address2 |
  | Doll 1   | 1   | Dolling 1| Union 1  |


  Scenario Outline: Russian Doll 2 - Create customer request
    Given I have extracted russian doll base firstname
    And I use customer header for doll
    And I have the data to create Russian Doll with "<lastName>","<phone>","<address1>","<address2>"
    When I create post request to create russian doll one
    Then I get status code 201 from doll
    And response body Russian Doll one should contain
      | firstName |
      | lastName  |
      | phone     |
      | addresses |
      | id        |

    Examples:
      | lastName | phone | address1 | address2 |
      | Doll 2   | 2     | Dolling 2| Union 2  |


    Scenario Outline: Create SOAP request to Walmart
      Given I have xml content
      When I create POST request to Walmart
      Then I get 403 from Walmart
      And getting "<message>" in the body with "<code>" for SOAP
      Examples:
        | message          | code |
        | Account Inactive | 403  |