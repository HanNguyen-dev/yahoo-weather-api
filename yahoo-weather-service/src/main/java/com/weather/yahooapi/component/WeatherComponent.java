package com.weather.yahooapi.component;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Base64;
import java.util.Date;
import java.util.Base64.Encoder;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import com.weather.yahooapi.domain.CityForecasts;
import com.weather.yahooapi.domain.Forecast;

@Component
public class WeatherComponent {
    final RestTemplate restTemplate;

    @Value("${app.appId}")
    private String appId;
    @Value("${app.consumerKey}")
    private String consumerKey;
    @Value("${app.consumerSecret}")
    private String consumerSecret;
    @Value("${app.baseUrl}")
    private String url;

    public WeatherComponent() {
        this.restTemplate = new RestTemplate();
    }

    public CityForecasts getTheWeather(String city) {
        long timestamp = new Date().getTime() / 1000;
        byte[] nonce = new byte[32];
        Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");

        List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");

        try {
            parameters.add("location=" + URLEncoder.encode(city, "UTF-8"));
            parameters.add("format=json");
        } catch(Exception e) {
            System.err.print("Unable to encode location");
        }


        Collections.sort(parameters);

        StringBuffer parametersList = new StringBuffer();
        for (int i = 0; i < parameters.size(); i++) {
            parametersList.append(
                ((i > 0) ? "&" : "") +
                parameters.get(i));
        }

        String signatureString = null;

        try {
            signatureString = "GET&" +
                URLEncoder.encode(url, "UTF-8") + "&" +
                URLEncoder.encode(parametersList.toString(), "UTF-8");
        } catch(Exception e) {
            System.err.print("Unable to encode url");
        }

        String signature = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(
                (consumerSecret + "&").getBytes(),
                "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());
            Encoder encoder = Base64.getEncoder();
            signature = encoder.encodeToString(rawHMAC);
        } catch (Exception e) {
            System.err.println("Unable to append signature");
            System.exit(0);
        }

        String authorizationLine = "OAuth " +
            "oauth_consumer_key=\"" + consumerKey + "\", " +
            "oauth_nonce=\"" + oauthNonce + "\", " +
            "oauth_timestamp=\"" + timestamp + "\", " +
            "oauth_signature_method=\"HMAC-SHA1\", " +
            "oauth_signature=\"" + signature + "\", " +
            "oauth_version=\"1.0\"";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizationLine);
        headers.add("X-Yahoo-App-Id", appId);
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>("header", headers);
        CityForecasts cityForecasts = restTemplate.exchange(URI.create(url + "?location=" + city + "&format=json"), HttpMethod.GET, entity, CityForecasts.class).getBody();

        return cityForecasts;
    }

}