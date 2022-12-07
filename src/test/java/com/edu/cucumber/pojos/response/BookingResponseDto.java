package com.edu.cucumber.pojos.response;

import com.edu.cucumber.pojos.request.Booking;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookingResponseDto {
    @JsonProperty("bookingid")
    private Long bookingId;
    private Booking booking;
}
