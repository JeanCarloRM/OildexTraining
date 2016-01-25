package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.PropertiesReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jean Carlo Rodriguez on 1/21/2016.
 */
public class APIManager {
    private static APIManager instance;
    private static CloseableHttpClient httpClient;
    private static String authorization;
    private static final PropertiesReader READER = PropertiesReader.instance();

    public static APIManager getInstance() {

        if (instance == null) {
            instance = new APIManager();
            authorization = READER.getAuthorizationString();

        }
        return instance;
    }

    public String post(String endPoint, String jsonData) throws IOException {
        httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(endPoint);
        postRequest.addHeader("Authorization", authorization);
        postRequest.addHeader("accept", "application/json");
        postRequest.setEntity(new StringEntity(jsonData));
        HttpResponse response = httpClient.execute(postRequest);
        return status(response);
    }

    public String status(HttpResponse response) throws IOException {
        String result = "";
        String output;
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));


        //Get the json response
        while ((output = br.readLine()) != null) {
            if (output.contains("{\"")) {
                System.out.println(output);
                result = output;
                break;
            }
        }
        httpClient.close();
        return result;
    }
}
