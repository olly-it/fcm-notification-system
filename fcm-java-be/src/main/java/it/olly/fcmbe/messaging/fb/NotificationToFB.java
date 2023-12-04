package it.olly.fcmbe.messaging.fb;

import java.util.Map;

/**
 * NOTE "data" must be a json object
 * 
 * @author olly
 *
 */
public class NotificationToFB {
    private Map<String, Object> data;
    private String to;

    public NotificationToFB() {
    }

    public NotificationToFB(Map<String, Object> data, String to) {
        this.data = data;
        this.to = to;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}