
package com.wants.market.utils.geo

import com.wants.market.core.domain.User
import com.wants.market.core.mapper.UserRegionMapper
import com.wants.market.user.dto.LocationRequest
import com.wants.market.user.service.SessionService
import spock.lang.Specification

class GeocodingServiceTest extends Specification {
    ReverseGeocodingService reverseGeocodingService = Mock()
    SessionService sessionService = Mock()
    UserRegionMapper userRegionMapper = Mock()
    GeocodingService geocodingService = new GeocodingService(reverseGeocodingService, sessionService, userRegionMapper)

    def "주소 매치 테스트 - 성공"() {
        given:
        LocationRequest locationRequest = new LocationRequest(127.075069, 37.5378484)

        ReverseGeoData reverseGeoData = new ReverseGeoData()
        ReverseGeoData.Address address = new ReverseGeoData.Address()
        address.name = "서울"
        address.region = new ReverseGeoData.Region()
        address.region.area3 = new ReverseGeoData.Area()
        address.region.area3.name = "석촌동"

        reverseGeoData.results = new ArrayList<>()
        reverseGeoData.results.add(address)

        reverseGeocodingService.findAddressByCoords(locationRequest) >> reverseGeoData

        User user = new User()
        user.address = "석촌동"
        sessionService.getLoggedInUserFromDatabase() >> user

        when:
        def matched = geocodingService.matchAddress(locationRequest)

        then:
        matched
        println matched
    }

}
