package com.wants.market.geo.controller;

import com.wants.market.user.dto.LocationRequest;
import com.wants.market.utils.geo.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GeoController {

    private final GeocodingService geocodingService;

    @GetMapping("/geocoding")
    public boolean geocoderService(@ModelAttribute LocationRequest locationRequest) {
        return geocodingService.matchAddress(locationRequest);
    }
}
