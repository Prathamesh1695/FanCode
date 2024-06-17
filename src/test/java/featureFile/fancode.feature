Feature: Verify user todos completion in FanCode city

  Scenario: Verify users have more than 50% of their todos completed
    Given Users belong to the city FanCode
    Then Each user should have more than 50% of their todos completed

    