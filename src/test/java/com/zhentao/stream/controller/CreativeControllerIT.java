package com.zhentao.stream.controller;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.springframework.http.MediaType;

public class CreativeControllerIT extends BaseIT {

    @Test
    public void testGetXmlOk() {
        given().header("Accept", MediaType.APPLICATION_XML_VALUE).expect().statusCode(200)
                                        .body(containsString("<id>1</id>")).when().get(HOST + "/rest/creatives/1");
    }

    @Test
    public void testGetJsonOk() {
        expect().statusCode(200).body(containsString("\"id\"")).when().get(HOST + "/rest/creatives/1");
    }

    @Test
    public void testGetJsonNotFound() {
        expect().statusCode(404).body(containsString("Creative is not found with id")).when().get(HOST + "/rest/creatives/100");
    }

    @Test
    public void testGetCreativesCreatedAfterOk() {

        expect().statusCode(200).body(containsString("\"id\"")).when().get(HOST + "/rest/creatives?after=2000-01-01 23:10:10");
    }

    @Test
    public void testStreamCreativesCreatedAfterOk() {

        expect().statusCode(200).body(containsString("\"id\"")).when().get(HOST + "/rest/creatives/stream?after=2000-01-01 23:10:10");
    }
}
