package com.edu.cucumber.helpers;

import com.edu.cucumber.pojos.AuthDto;
import com.edu.cucumber.pojos.TokenDto;
import io.restassured.specification.RequestSpecification;

public class Authentificator {

    public static TokenDto getToken(RequestSpecification specification, AuthDto authDto) {
        return specification
                .body(authDto)
                .post(Constants
                        .AUTH_CONTROLLER_PATH)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .as(TokenDto.class);
    }
}
