package com.gridnine.testing;

import com.gridnine.testing.dao.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.impl.FlightFilteringImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        String withoutFilter = "Flight list without any filter";
        printResultList(withoutFilter, flightList);

        List<Flight> departureBeforeNowFilter = new FlightFilteringImpl(flightList)
                .departureBeforeNow()
                .build();
        String withoutDepartureBeforeNow = "Flight list without flights departure before now";
        printResultList(withoutDepartureBeforeNow, departureBeforeNowFilter);

        List<Flight> arrivalDateBeforeDepartureDateFilter = new FlightFilteringImpl(flightList)
                .segmentsWithArrivalDateBeforeDepartureDate()
                .build();
        String withoutArrivalBeforeDeparture = "Flight list without flights arrival before departure";
        printResultList(withoutArrivalBeforeDeparture, arrivalDateBeforeDepartureDateFilter);

        List<Flight> totalTimeOnTheGroundExceedsTwoHoursFilter = new FlightFilteringImpl(flightList)
                .flightsWhereTotalTimeOnTheGroundExceedsTwoHours()
                .build();
        String withoutTimeOnTheGroundExceedsTwoHours = "Flight list without flights where total time on the ground exceeds two hours";
        printResultList(withoutTimeOnTheGroundExceedsTwoHours, totalTimeOnTheGroundExceedsTwoHoursFilter);
    }

    public static void printResultList(String text, List<Flight> flights) {
        System.out.println(text + ":");
        flights.forEach(System.out::println);
    }
}