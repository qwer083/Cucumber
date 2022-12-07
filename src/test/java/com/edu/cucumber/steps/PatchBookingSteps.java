package com.edu.cucumber.steps;

import com.edu.cucumber.helpers.Constants;
import com.edu.cucumber.pojos.request.Booking;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class PatchBookingSteps {
    private BookingBaseSteps bookingBaseSteps = new BookingBaseSteps();
    private static Booking BookingAfterPatch;

    @Then("change booking earlier create booking and assertResult:")
    public void changeBookingEarlierCreateBookingAndAssertResult(DataTable dataTable) {
        Map<Object, Object> map = dataTable.asMap(String.class, Object.class);
        Booking booking =
                BookingBaseSteps.requestSpecification
                        .body(map)
                        .patch(Constants.BOOKING_CONTROLLER_PATH + "/" + BookingBaseSteps.createdBooking.getId())
                        .then()
                        .statusCode(200)
                        .log()
                        .body()
                        .extract()
                        .as(Booking.class);

        if (map.containsKey("firstname")) {
            Assertions.assertEquals(map.get("firstname"), booking.getFirstname());
        }
        if (map.containsKey("lastname")) {
            Assertions.assertEquals(map.get("lastname"), booking.getLastname());
        }
        if (map.containsKey("depositPaid")) {
            Assertions.assertEquals(map.get("depositPaid"), booking.isDepositPaid());

        }
        if (map.containsKey("bookingDates")) {
            String[] dates = map.get("bookingDates").toString().split(",");
            String in = dates[0];
            String out = dates[1];
            Booking.BookingDates dates1 = new Booking.BookingDates(LocalDate.parse(in), LocalDate.parse(out));
            Assertions.assertEquals(dates1, booking.getBookingDates());
        }
        if (map.containsKey("additionalneeds")) {
            Assertions.assertEquals(map.get("additionalneeds"), booking.getAdditionalneeds());
        }
        if (map.containsKey("totalPrice")) {
            Assertions.assertEquals(map.get("totalPrice"), booking.getTotalPrice());
        }
    }
}