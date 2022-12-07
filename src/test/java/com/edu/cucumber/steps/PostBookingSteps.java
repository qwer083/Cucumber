package com.edu.cucumber.steps;

import com.edu.cucumber.helpers.BookingHelper;
import com.edu.cucumber.helpers.Constants;
import com.edu.cucumber.pojos.request.Booking;
import com.edu.cucumber.pojos.response.BookingResponseDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class PostBookingSteps {
    private static Booking bookingForCreateBookingSteps;

    @When("create booking dto with parameters:")
    public void createBookingDtoWithParameters(DataTable dataTable) {
        Map<String, String> t = dataTable.asMap(String.class, String.class);
        Booking body = BookingHelper.bookingFromMap(t);
        BookingResponseDto bookingResponseDto =

                BookingBaseSteps.requestSpecification
                        .body(body)
                        .post(Constants.BOOKING_CONTROLLER_PATH)
                        .then()
                        .statusCode(200)
                        .log()
                        .body()
                        .extract()
                        .as(BookingResponseDto.class);
        bookingForCreateBookingSteps = bookingResponseDto.getBooking();
        bookingForCreateBookingSteps.setId(bookingResponseDto.getBookingId());

    }

    @Then("Assert that books equeal books with same id from database")
    public void assertBookAfterPost() {
        Assertions.assertEquals(BookingHelper.getBooking(BookingBaseSteps.requestSpecification, bookingForCreateBookingSteps.getId()), bookingForCreateBookingSteps);
    }

}
