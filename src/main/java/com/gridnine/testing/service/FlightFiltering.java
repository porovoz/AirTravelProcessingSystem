package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFiltering {
    List<Flight> build();
    FlightFiltering departureBeforeNow();
    FlightFiltering segmentsWithArrivalDateBeforeDepartureDate();
    FlightFiltering flightsWhereTotalTimeOnTheGroundExceedsTwoHours();
}
