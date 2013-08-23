package com.zhentao.stream.manual;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class TestStreaming {

    public static void main(String[] args) throws IOException {
        URL obj = new URL("http://localhost:8080/rest/creatives/stream?after=2012-12-12%2005:06:07");
        URLConnection conn = obj.openConnection();

        JsonFactory jsonFactory = new JsonFactory();

        InputStream stream = conn.getInputStream();

        JsonParser jp = jsonFactory.createParser(stream);
        jp.nextToken();
        while (jp.nextToken() != JsonToken.END_ARRAY) {
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jp.getCurrentName();
                jp.nextToken();
                if (currentName.equals("id")) {
                    System.out.printf("id: %d%n", jp.getLongValue());
                } else if (currentName.equals("screenShotLocation")) {
                    System.out.printf("screenShotLocation: %s%n", jp.getText());
                } else if (currentName.equals("sound")) {
                    System.out.printf("sound: %s%n", jp.getBooleanValue());
                } else if (currentName.equals("creativeUrl")) {
                    System.out.printf("creativeUrl: %s%n", jp.getText());
                } else if (currentName.equals("creativeHtml")) {
                    System.out.printf("creativeHtml: %s%n", jp.getText());
                } else if (currentName.equals("largestSwf")) {
                    System.out.printf("largestSwf: %s%n", jp.getText());
                } else if (currentName.equals("largestImg")) {
                    System.out.printf("largestImg: %s%n", jp.getText());
                } else if (currentName.equals("clickThroughUrl")) {
                    System.out.printf("clickThroughUrl: %s%n", jp.getText());
                } else if (currentName.equals("landingUrl")) {
                    System.out.printf("landingUrl: %s%n", jp.getText());
                } else if (currentName.equals("height")) {
                    System.out.printf("height: %d%n", jp.getIntValue());
                } else if (currentName.equals("width")) {
                    System.out.printf("width: %d%n", jp.getIntValue());
                } else if (currentName.equals("loadTime")) {
                    System.out.printf("loadTime: %d%n", jp.getIntValue());
                } else if (currentName.equals("md5")) {
                    System.out.printf("md5: %s%n", jp.getText());
                } else if (currentName.equals("created")) {
                    System.out.printf("created: %d%n", jp.getLongValue());
                }
            }
        }
    }
}
