package com.myapplication.weatherdemo.restclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@RestClientTest(HttpClient.class)
public class HttpClientTest {

    @Test
    public void testHappyPath() {

    }
}
