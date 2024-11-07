Feature: order purchasing from e-commerce website

Scenario Outline: submit order positive scenario
    Given user is logged in with username <username> and password <password>
    When add <productName> to the cart
    And checkout <productName> and submit order
    Then "Thankyou for the order." is displayed on confirmation page

Examples: 
| username            | password     | productName |
| againemail@teml.net | Password1234 | ZARA COAT 3 |