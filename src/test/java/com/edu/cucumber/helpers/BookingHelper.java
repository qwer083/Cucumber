package com.edu.cucumber.helpers;

import com.edu.cucumber.pojos.request.Booking;
import com.edu.cucumber.pojos.response.BookingResponseDto;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import java.util.Map;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;

public class BookingHelper {
    private static final Faker faker = new Faker();

    public static BookingResponseDto createBooking(RequestSpecification specification, Booking.BookingDates bookingDates) {
        Booking request = new Booking();
        request.setBookingDates(bookingDates);
        request.setFirstname(faker.name().firstName());
        request.setLastname(faker.name().lastName());
        request.setAdditionalneeds(faker.internet().emailAddress() + faker.internet().ipV4Address());
        request.setTotalPrice(faker.random().nextLong(1, 9999));
        request.setDepositPaid(true);

        BookingResponseDto responseDto =
                given(specification)
                        .body(request)
                        .when()
                        .post(Constants.BOOKING_CONTROLLER_PATH)
                        .then()
                        .statusCode(200)
                        .log()
                        .body()
                        .extract()
                        .as(BookingResponseDto.class);

        Assertions.assertEquals(responseDto.getBooking(), request,
                String.format("Параметры сгенерированной сущности отличаются от переданных." +
                        " Сгенерировано: %s \n Получено в ответе: %s", request.toString(), responseDto.getBooking().toString()));
        Assertions.assertNotNull(responseDto.getBookingId(), "При генерации бронирования id не был сгенерирован");
        return responseDto;
    }

    public static Booking getBooking(RequestSpecification specification, Long bookingId) {
        Booking responseDto = specification
                .when()
                .get(Constants.BOOKING_CONTROLLER_PATH + "/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Booking.class);
        return responseDto;
    }

    public static Booking bookingFromMap(Map<String, String> map) {
        Booking booking = new Booking();
        Booking.BookingDates bookingDates = new Booking.BookingDates();
        String[] dates = map.get("bookingDates").split(",");
        String in = dates[0];
        String out = dates[1];
        bookingDates.setCheckIn(LocalDate.parse(in));
        bookingDates.setCheckOut(LocalDate.parse(out));
        booking.setFirstname(map.get("firstname"));
        booking.setLastname("lastname");
        booking.setDepositPaid(Boolean.parseBoolean(map.get("depositPaid")));
        booking.setTotalPrice(Long.parseLong(map.get("totalPrice")));
        booking.setAdditionalneeds(map.get("additionalneeds"));
        booking.setBookingDates(bookingDates);
        return booking;

    }
}
