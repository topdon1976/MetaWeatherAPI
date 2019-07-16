package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetWeatherStepDefinitions {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    private String ENDPOINT_GET_LOCATION_BY_QUERY = "https://www.metaweather.com/api/location/search";
    private String ENDPOINT_GET_WEATHER_BY_WOEID = "https://www.metaweather.com/api/location/";
    private String sURL;

    @Given("^a location search by query \"([^\"]*)\"$")
    public void a_location_search_by_query(String query) {
        request = given().param("query", query);
    }

    @When("^a user retrieves the location details$")
    public void a_user_retrieves_the_location_details() {

        response = request.when().get(ENDPOINT_GET_LOCATION_BY_QUERY);
        System.out.println("response: " + response.prettyPrint());

    }

    @Then("^the status code is (\\d+)$")
    public void the_status_code_is(int statusCode) {
        json = response.then().statusCode(statusCode);
    }


    @And("^the woe id is returned \"([^\"]*)\"$")
    public void theWoeIdIsReturned(int woe_id) {
        json = response.then().body("[0].woeid", equalTo(woe_id));

    }

    @Given("^a user calls the api to get the weather for london - (\\d+)$")
    public void aUsersCallsTheApiToGetTheWeatherFor(int woeid) {

        sURL = ENDPOINT_GET_WEATHER_BY_WOEID + woeid;
        request = given();

    }

    @When("^a user retrieves the weather details$")
    public void aUserRetrievesTheWeatherDetails() {

        response = request.when().get(sURL);
        System.out.println("response: " + response.prettyPrint());

    }

    @And("^the today's weather is \"([^\"]*)\"$")
    public void theTodaySWeatherIs(String expectedState) {

        String json = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                expectedState,
                jsonPath.getString("consolidated_weather[0].weather_state_name"));

    }


    @And("^today's weather is \"([^\"]*)\"$")
    public void todaySWeatherIs(String expectedState)  {

        String json = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                expectedState,
                jsonPath.getString("consolidated_weather[0].weather_state_name"));

    }

    @And("^tomorrow's weather is \"([^\"]*)\"$")
    public void tomorrowSWeatherIs(String expectedState)   {

        String json = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                expectedState,
                jsonPath.getString("consolidated_weather[1].weather_state_name"));

    }


    @And("^tomorrow plus one weather is \"([^\"]*)\"$")
    public void tomorrowPlusOneWeatherIs(String expectedState)   {
        String json = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                expectedState,
                jsonPath.getString("consolidated_weather[2].weather_state_name"));
    }

    @And("^tomorrow plus two weather is \"([^\"]*)\"$")
    public void tomorrowPlusTwoWeatherIs(String expectedState)   {

        String json = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(json);
        Assert.assertEquals(
                expectedState,
                jsonPath.getString("consolidated_weather[3].weather_state_name"));

    }


}