package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Connection {

    private String baseURL = "https://apichallenges.eviltester.com/sim";

    private HttpURLConnection gerarHttp (String resource, String method) throws URISyntaxException, IOException {
        String sURI = this.baseURL + resource;
        URI uri = new URI(sURI);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(5000);
        return conn;
    }

    public StringBuilder getEntidades() throws URISyntaxException, IOException {
        StringBuilder response = null;
        HttpURLConnection connection = gerarHttp("/entities", "GET");

        int responseCode = connection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            response = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null){
                response.append(responseLine);
            }

            connection.disconnect();
            return response;
        }

        return null;
    }

}
