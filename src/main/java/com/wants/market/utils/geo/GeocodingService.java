package com.wants.market.utils.geo;

import com.wants.market.core.domain.User;
import com.wants.market.core.mapper.UserRegionMapper;
import com.wants.market.user.dto.LocationRequest;
import com.wants.market.user.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GeocodingService {
    private final ReverseGeocodingService reverseGeoCodingService;
    private final SessionService sessionService;
    private final UserRegionMapper userRegionMapper;

    public boolean matchAddress(LocationRequest request) {
        ReverseGeoData reverseGeoData = reverseGeoCodingService.findAddressByCoords(request);
        List<ReverseGeoData.Address> addresses = reverseGeoData.getResults();

        boolean matched = false;
        User user = sessionService.getLoggedInUserFromDatabase();

        for(ReverseGeoData.Address data : addresses) {
            ReverseGeoData.Region region = data.getRegion();

            if(user.getAddress().equals(region.area3.name)) {
                matched = true;
                break;
            }
        }

        userRegionMapper.insertLocation(request.getLongitude(), request.getLatitude(), matched ? "Y" : "N", user.getId());
        return matched;
    }
}
