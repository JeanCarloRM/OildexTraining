package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.Properties;

public class CommonUtils {
    public CommonUtils() {
    }

    public String postRequest(String url, String authorization, String jsonData) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(url + ".json");
        postRequest.addHeader("Authorization", authorization);
        postRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity(jsonData);
        postRequest.setEntity(input);
        HttpResponse response = httpClient.execute(postRequest);
        return status(response,httpClient);
    }

    public String getRequest(String url, String authorization) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url + ".json");
        getRequest.addHeader("Authorization", authorization);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);
        return status(response,httpClient);
    }

    public String getRequestId(String url, String authorization, String id) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url + "/"+id+".json");
        getRequest.addHeader("Authorization", authorization);
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(getRequest);
        return status(response,httpClient);
    }

    public String putRequest(String url, String authorization,String id, String jsonData) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut putRequest = new HttpPut(url + "/"+id+".json");
        putRequest.addHeader("Authorization", authorization);
        putRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity(jsonData);
        putRequest.setEntity(input);
        HttpResponse response = httpClient.execute(putRequest);
        return status(response,httpClient);
    }

    public String deleteRequest(String url, String authorization, String id) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete deleteRequest = new HttpDelete(url + "/"+id+".json");
        deleteRequest.addHeader("Authorization", authorization);
        deleteRequest.addHeader("accept", "application/json");
        HttpResponse response = httpClient.execute(deleteRequest);
        return status(response,httpClient);
    }

    public String status(HttpResponse response,CloseableHttpClient httpClient)  throws IOException {
        String result = "";
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        String output;

        //Get the json response
        while ((output = br.readLine()) != null) {
            if(output.contains("{\"")) {
                System.out.println(output);
                result = output;
                break;
            }
        }
        httpClient.close();
        return result;
    }
}
