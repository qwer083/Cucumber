package com.edu.cucumber.pojos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Booking {
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Long id;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;
    @JsonProperty("totalprice")
    private long totalPrice;
    @JsonProperty("additionalneeds")
    private String additionalneeds;

    @Data
    @NoArgsConstructor

    public static class BookingDates {
        @JsonProperty("checkin")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate checkIn;
        @JsonProperty("checkout")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate checkOut;

        public BookingDates(LocalDate checkIn, LocalDate checkOut) {
            this.checkIn = checkIn;
            this.checkOut = checkOut;
        }
    }


}

