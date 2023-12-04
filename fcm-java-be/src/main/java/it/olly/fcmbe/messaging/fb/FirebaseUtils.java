package it.olly.fcmbe.messaging.fb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FirebaseUtils {
    private static final String SERVICE_URL = "https://fcm.googleapis.com/fcm/send";

    @Autowired
    private ObjectMapper om;

    @Value("${firebase.server.key}")
    private String serverKey;

    /**
     * 
     * @param receiverToken device token you get on webpage
     * @param message
     * @throws Exception
     */
    public String sendMessageViaHTTP(String receiverToken, String message) throws Exception {
        Map<String, Object> notification = new HashMap<>();
        notification.put("message", message);
        notification.put("date", new Date());
        String httpBody = om.writeValueAsString(new NotificationToFB(notification, receiverToken));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVICE_URL))
                .timeout(Duration.ofMinutes(1))
                .header("Authorization", "key=" + serverKey)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(httpBody))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.statusCode() + " - " + response.body();
    }
}
