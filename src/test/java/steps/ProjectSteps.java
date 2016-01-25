package steps;

import api.APIManager;
import api.APIProjectMethods;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

/**
 * Created by Jean Carlo Rodriguez on 1/21/2016.
 */
public class ProjectSteps {

    private static String projectName;
    private APIProjectMethods apiProjectMethods = APIProjectMethods.getInstance();
    private static String jsonResponse;

    @Given("^I will use the user \"([^\"]*)\" to make the requests$")
    public void IWillUseTheUserToMakeTheRequests(String user) {
        //
        System.out.println("");
        System.out.println("Using the user: " + user + " for API");
    }

    @When("^I create a project \"([^\"]*)\" using the API$")
    public void ICreateAProjectUsingTheAPI(String projectName) throws IOException {
        this.projectName = projectName;
        jsonResponse = apiProjectMethods.createProject(projectName);
    }

    @Then("^the Project should be obtained with the correct values$")
    public void theProjectShouldBeObtainedWithTheCorrectValues() {
        //here we can Use a Json reader to assert all the fields in the Json response
        System.out.println("Executing the asserts! for the project: " + this.projectName);
        System.out.println("the response: " + jsonResponse);
    }

    @After("@createProject")
    public void removeAProject() {
        //just for probe
        System.out.println("Removing the project: " + this.projectName);
    }
}