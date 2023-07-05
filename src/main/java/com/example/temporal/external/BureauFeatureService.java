package com.example.temporal.external;

import com.example.temporal.dto.BureauReport;
import com.example.temporal.dto.ParsedBureauReport;
import com.example.temporal.dto.UserData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@Service
public class BureauFeatureService {

    public ParsedBureauReport getBureauReport(BureauReport bureauReport) {

        String apiUrl = "http://localhost:9097/featureEngine/services/feature/health";
        HttpURLConnection connection;

        try {
            // Create URL object
            URL url = new URL(apiUrl);

            // Create connection object
            connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                System.out.println("Response from the feature service: " + response.toString());
                return new ParsedBureauReport();
            } else {
                connection.disconnect();
                System.out.println("Request failed. Response Code: " + responseCode);
                throw new RuntimeException("Feature Service failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Feature Service failed");
        }

    }


}
