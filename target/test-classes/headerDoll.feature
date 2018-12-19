Feature: RussianDoll header testing


  Scenario Outline: Russian Doll 3 - Create customer request with dynamic header
    Given I have extracted russian doll base firstname
    And I use customer dynamic header for doll
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
  | Doll 3   | 3     | Dolling 3| Union 3  |

