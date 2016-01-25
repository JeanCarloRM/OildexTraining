Feature: As an API consumer
  I want to manage the projects using the REST API

  Scenario: project using REST
    When I did a post request:
      | Content  | nameProject|
      | Icon     | 6          |
    Then The response should contain the data with the string value "Content" and "nameProject"

#    When I did a put request with the id "3494145" and the values:
#      | Content  | NameModified|
#      | Icon     | 4           |
#    Then The response should contain the data with the string value "Content" and "NameModified"
#
#    When I did a delete request with the id "3494146"
#    Then The response should contain "Deleted" and "true"
#
#    When I did a delete request with the id "3494146"
#    Then The response should contain "Id" and "3494146"
#
#    When I did a get all request
#    Then Response is not empty


