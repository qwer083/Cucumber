package com.edu.cucumber.steps;

import com.edu.cucumber.helpers.BookingHelper;
import com.edu.cucumber.helpers.Constants;
import com.edu.cucumber.pojos.AuthDto;
import com.edu.cucumber.pojos.TokenDto;
import com.edu.cucumber.pojos.request.Booking;
import com.edu.cucumber.pojos.response.BookingResponseDto;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;

public class BookingBaseSteps {
    public static RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.JSON).baseUri(Constants.BASE_PATH).log().all();
    public static Booking createdBooking;

    @When("authorize and add token to spec")
    public void authAndAddTokenToSpec() {
        AuthDto authDto = new AuthDto(Constants.PASSWORD, Constants.USERNAME);
        TokenDto tokenDto = requestSpecification
                .body(authDto)
                .post(Constants
                        .AUTH_CONTROLLER_PATH)
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .as(TokenDto.class);

        requestSpecification.cookie("token", tokenDto.getToken());
    }

    @When("create booking with random parameters")
    public void createBookingWithRandomParameters() {
        Booking.BookingDates dates = new Booking.BookingDates(
                LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 18));
        BookingResponseDto responseDto = BookingHelper.createBooking(requestSpecification, dates);
        createdBooking = responseDto.getBooking();
        createdBooking.setId(responseDto.getBookingId());
    }


}
