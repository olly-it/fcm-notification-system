package it.olly.fmcbe;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class OneShotMessageTest {
    // your-server-key-from-cloud-messaging-config

    @Value("${firebase.server.key}")
    private String serverKey;

    @Test
    void sendMessageViaHTTP() throws Exception {

        // device-token-you-get-on-webpage
        String DEVICE_REG_TOKEN = "device-token-you-get-on-webpage";
        String SERVICE_URL = "https://fcm.googleapis.com/fcm/send";

        String body = "{\n" + "   \"data\": {\n" + "     \"notification\": {\n"
                + "         \"title\": \"FCM Message\",\n" + "         \"body\": \"This is an FCM Message - "
                + (new Date()) + "\",\n" + "         \"icon\": \"/itwonders-web-logo.png\",\n" + "     }\n" + "   },\n"
                + "   \"to\": \"" + DEVICE_REG_TOKEN + "\"\n" + " }";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SERVICE_URL))
                .timeout(Duration.ofMinutes(1))
                .header("Authorization", "key=" + serverKey)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
