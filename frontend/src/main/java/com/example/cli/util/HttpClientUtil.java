package com.example.cli.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientUtil {

    private static final String BASE_URL = "http://localhost:8080/api"; // 定义基础 URL
    private static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * Send a GET request to the specified endpoint.
     */
    public static String sendGet(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint); // 使用基础 URL 拼接端点
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", CONTENT_TYPE_JSON);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new Exception("GET request failed with response code: " + responseCode);
        }
    }

    /**
     * Send a POST request to the specified endpoint with JSON payload.
     */
    public static String sendPost(String endpoint, String jsonPayload) throws Exception {
        return sendRequestWithBody("POST", endpoint, jsonPayload);
    }

    /**
     * Send a PUT request to the specified endpoint with JSON payload.
     */
    public static String sendPut(String endpoint, String jsonPayload) throws Exception {
        return sendRequestWithBody("PUT", endpoint, jsonPayload);
    }

    /**
     * Send a DELETE request to the specified endpoint.
     */
    public static String sendDelete(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint); // 使用基础 URL 拼接端点
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Accept", CONTENT_TYPE_JSON);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT || responseCode == HttpURLConnection.HTTP_OK) {
            return "DELETE request was successful.";
        } else {
            throw new Exception("DELETE request failed with response code: " + responseCode);
        }
    }

    /**
     * Send a request with a body (used for POST and PUT).
     */
    private static String sendRequestWithBody(String method, String endpoint, String jsonPayload) throws Exception {
        URL url = new URL(BASE_URL + endpoint); // 使用基础 URL 拼接端点
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", CONTENT_TYPE_JSON);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonPayload.getBytes());
            os.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new Exception(method + " request failed with response code: " + responseCode);
        }
    }
}