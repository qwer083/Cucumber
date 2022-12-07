Feature: example feature

  @getBooking
  Scenario: Get booking
    When create booking with random parameters
    Then get booking and assert that booking equal earlier create

  @createBooking
  Scenario: Create Booking
    When create booking dto with parameters:
      | firstname       | JON                   |
      | lastname        | DOU                   |
      | depositPaid     | true                  |
      | bookingDates    | 2022-12-12,2022-12-18 |
      | additionalneeds | theron                |
      | totalPrice      | 1                     |
    Then Assert that books equeal books with same id from database

  @PartEditBooking
  Scenario: Edit partial booking
    When authorize and add token to spec
    And create booking with random parameters
    Then change booking earlier create booking and assertResult:
      | firstname | newFirstName |
      | lastname  | newLastName  |

    @FullEditBooking
    Scenario: Edit full booking
      When authorize and add token to spec
      And create booking with random parameters
      Then change booking earlier create booking and assertResult:
        | firstname       | JON                   |
        | lastname        | DOU                   |
        | depositPaid     | true                  |
        | bookingDates    | 2022-12-12,2022-12-18 |
        | additionalneeds | theron                |
        | totalPrice      | 1                     |