package api;

import utils.PropertiesReader;

import java.io.IOException;

/**
 * Created by Jean Carlo Rodriguez on 1/21/2016.
 */
public class APIProjectMethods {
    private static APIProjectMethods instance;
    private static APIManager apiManager;
    private static PropertiesReader propertiesReader;

    public static APIProjectMethods getInstance() {

        if (instance == null) {
            instance = new APIProjectMethods();
            apiManager = APIManager.getInstance();
            propertiesReader = PropertiesReader.instance();
        }
        return instance;
    }

    public String createProject(String projectName) throws IOException {
        String jsonProject = "{\"Content\":\"" + projectName + "\"}";
        return apiManager.post(propertiesReader.getEndPoint() + ".json", jsonProject);
    }

}
