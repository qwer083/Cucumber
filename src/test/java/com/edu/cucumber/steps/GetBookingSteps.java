package com.edu.cucumber.steps;

import com.edu.cucumber.helpers.BookingHelper;
import com.edu.cucumber.pojos.request.Booking;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class GetBookingSteps {
    private BookingBaseSteps bookingBaseSteps = new BookingBaseSteps();



    @Then("^get booking and assert that booking equal earlier create$")
    public void getAndAssertBooking() {
        Booking getBooking = BookingHelper.getBooking(bookingBaseSteps.requestSpecification, bookingBaseSteps.createdBooking.getId());
        Assertions.assertEquals(bookingBaseSteps.createdBooking, getBooking, "Полученное бронирование отличается от созданного");
    }



}
