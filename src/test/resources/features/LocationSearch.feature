Feature: Get the where on earth id, woeid by query text or latitude and longitude coordinates

  Scenario Outline: User calls Location Search to get the Where on Earth ID  by query
  https://www.metaweather.com/api/location/search/?query=london

    Given a location search by query "<query>"
    When a user retrieves the location details
    Then the status code is 200
    And the woe id is returned "<woe_id>"

    Examples:
      |query | woe_id |
      |london | 44418|
      |glasgow | 21125|
      |Cardiff | 15127|
      |Salford | 33887|


  #@Automated
    #  Scenario Outline: User calls Location Search to get the Where on Earth ID  by latitude and longitude co-ordinates
  #https://www.metaweather.com/api/location/search/?lattlong=36.96,-122.02
#
#    Given a location search by latitude and longitude "<lattlong>"
#    When a user retrieves the location details
#    Then the status code is 200
#    And the woe id is returned "<woe_id>"
#
#    Examples:
#      |lattlong | woe_id |
#      |51.506321,-0.12714 | 44418|
#      |55.857800,-4.242510 | 21125|
#      |51.481251,-3.180730 | 15127|
#      |53.489731,-2.2843 | 33887|


  #@Automated
#  Scenario: User gets a 403 from the Location Search when no query string parameter is given
#
#    Given a location search with no parameter
#    When a user makes the call
#    Then the status code is 403

  #@Manual
#  Scenario: User gets no location details when a numeric query value is given
#
#    Given a location search by query 123
#    When a user makes the call
#    Then the status code is 200
#   And there will be no data in the response body


    #@Manual
#  Scenario: User gets a 500 status when a alphanumeric lattlong value is given
#
#    Given a location search by lattlong abc, a-122.02
#    When a user makes the call
#    Then the status code is 500

      #@Manual
#  Scenario: User gets a 403 status when no lattlong values are given
#
#    Given a location search by lattlong=
#    When a user makes the call
#    Then the status code is 403


 # @Manual
#  Scenario: Partial text searches will return multiple locations
#    lon returns titles for London, Barcelona and Long Beach
#
#    Given a location search by query "lon"
#    When a user retrieves the location details
#    Then the status code is 200
#    And three title objects are returned

