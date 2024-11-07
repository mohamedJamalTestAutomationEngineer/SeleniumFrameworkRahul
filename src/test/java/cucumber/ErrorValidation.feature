Feature: error validation log in with invalid credentials

@Validation
Scenario Outline: login negative scenario
    Given user is on landing page
    When I logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

Examples:
| username            | password      |
| againemail@teml.net | Password12345 |

