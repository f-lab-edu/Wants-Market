package com.wants.market.utils.geo;

import com.wants.market.user.dto.LocationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReverseGeocodingService {

    @Value("${geo.api.id}")
    private String API_KEY_ID;

    @Value("${geo.api.key}")
    private String API_KEY;

    @Value("${geo.api.url}")
    private String API_URL;

    public ReverseGeoData findAddressByCoords(LocationRequest locationRequest) {
        StringBuilder coords = new StringBuilder().append(locationRequest.getLongitude()).append(",").
                append(locationRequest.getLatitude());
        String output = "output=json";
        String url = new StringBuilder(API_URL).append("?coords=").append(coords).append("&").append(output).toString();

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set("X-NCP-APIGW-API-KEY-ID", API_KEY_ID);
        headers.set("X-NCP-APIGW-API-KEY", API_KEY);

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<ReverseGeoData> response = restTemplate.exchange(url, HttpMethod.GET, request, ReverseGeoData.class);
        return response.getBody();
    }

}
