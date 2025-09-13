package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class ConexaoEntidades {

    private String baseURL = "https://apichallenges.eviltester.com/sim";

    private HttpURLConnection gerarHttp(String resource, String method) throws URISyntaxException, IOException {
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

        if (responseCode == HttpURLConnection.HTTP_OK) {
            response = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine);
            }
        }
        connection.disconnect();
        return response;
    }

    public StringBuilder getEntendidadeId(int id) throws URISyntaxException, IOException {
        StringBuilder response = null;
        HttpURLConnection connection = gerarHttp("/entities/" + id, "GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            response = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine);
            }
        }else  if (responseCode == HttpURLConnection.HTTP_NOT_FOUND){
            throw new RuntimeException("Entidade com ID "+id+" não encontradada!");
        }else {
            response.append(responseCode);
        }

        connection.disconnect();
        return response;
    }

    public StringBuilder UrlFake (String URL) throws URISyntaxException, IOException {
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = gerarHttp( URL, "GET");

        int responseCode = connection.getResponseCode();
        response.append("URL final: "+baseURL+ URL+ "\nCódigo: "+responseCode);
        return response;
    }

    public StringBuilder insertEntidade(String post) throws URISyntaxException, IOException {
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = gerarHttp("/entities", "POST");

        connection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        byte[] bPost = post.getBytes();
        outputStream.write(bPost);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_CREATED){
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine);
            }
            System.out.println("Entidade criada com sucesso!");
        }else response.append(responseCode);

        return response;
    }

}


