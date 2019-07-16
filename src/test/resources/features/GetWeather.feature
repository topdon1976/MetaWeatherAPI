Feature: Get Weather Forecast by Where on Earth ID
  The forecast will be returned for the specified location for today and the next five days
  The assumption is that the consolidated_weather is returned in date order starting from today i.e. today - consolidated_weather[0]

  Scenario: User gets the weather forecast for London for today and the next five days

    Given a user calls the api to get the weather for london - 44418
    When a user retrieves the weather details
    Then the status code is 200
    And today's weather is "Heavy Cloud"
    And tomorrow's weather is "Showers"
    And tomorrow plus one weather is "Clear"
    And tomorrow plus two weather is "Light Cloud"

#   @Manual
#    Scenario: User gets a 404 when a call is made to get the forecast for a woeid which does not exist
#
#    Given a location call for woeid 123456
#    When a user makes the call
#    Then the status code is 404

  #   @Manual
#    Scenario: User gets a 404 when a call is made to get the forecast for a non numeric woeid
#
#    Given a location call for woeid abcd
#    When a user makes the call
#    Then the status code is 404

#  @Automated
#    Scenario: User gets the applicable date for the weather forecast for Glasgow for today and the next five days
#
#    Given a location call for woeid 21125
#    When a user makes the call
#    Then the status code is 200
#    And the applicable date for the first consolidated_weather object is sysdate
#    And the applicable date for the last consolidated_weather object is sysdate + 5



