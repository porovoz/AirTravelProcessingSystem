package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.service.FlightFiltering;
import com.gridnine.testing.service.impl.FlightFilteringImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightFilteringImplTest {
    static LocalDateTime nowPlusTwoHoursDateTime = LocalDateTime.now().plusHours(2);
    static LocalDateTime tomorrowDateTime = LocalDateTime.now().plusDays(1);

    List<Flight> normalFlightsAfterCurrentDateTime;
    List<Flight> flightsWithDepartureBeforeNow;
    List<Flight> flightsWithArrivalDateTimeBeforeDepartureDateTime;
    List<Flight> flightsWithTotalTimeOnTheGroundExceedsTwoHours;
    List<Flight> testFlights;

    public void initNormalFlightsAfterCurrentDateTime() {
        normalFlightsAfterCurrentDateTime = new ArrayList<>();
        LocalDateTime nowPlusTwoHoursDateTime = LocalDateTime.now().plusHours(2);
        LocalDateTime tomorrowDateTime = LocalDateTime.now().plusDays(1);

        // A normal flight with three hour duration
        List<Segment> flightOne = new ArrayList<>();
        flightOne.add(new Segment(nowPlusTwoHoursDateTime, nowPlusTwoHoursDateTime.plusHours(3)));
        Flight normalFlight = new Flight(flightOne);

        // A normal multi-segment flight
        List<Segment> flightTwo = new ArrayList<>();
        flightTwo.add(new Segment(tomorrowDateTime, tomorrowDateTime.plusHours(2)));
        flightTwo.add(new Segment(tomorrowDateTime.plusHours(3), tomorrowDateTime.plusHours(7)));
        flightTwo.add(new Segment(tomorrowDateTime.plusHours(7).plusMinutes(30), tomorrowDateTime.plusHours(10)));
        Flight normalMultiSegmentFlight = new Flight(flightTwo);

        normalFlightsAfterCurrentDateTime.add(normalFlight);
        normalFlightsAfterCurrentDateTime.add(normalMultiSegmentFlight);
    }

    public void initFlightsWithDepartureBeforeNow() {
        flightsWithDepartureBeforeNow = new ArrayList<>();
        LocalDateTime oneDayBeforeNowDateTime = LocalDateTime.now().minusDays(1);
        LocalDateTime threeDaysBeforeNowDateTime = LocalDateTime.now().minusDays(3);

        // A flight departing in the past
        List<Segment> flightOne = new ArrayList<>();
        flightOne.add(new Segment(oneDayBeforeNowDateTime, oneDayBeforeNowDateTime.plusHours(4)));
        Flight flightWithDepartureBeforeNow = new Flight(flightOne);

        // A flight with departure in past and normal arrival
        List<Segment> flightTwo = new ArrayList<>();
        flightTwo.add(new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(5)));
        Flight flightWithDepartureInPastAndNormalArrival = new Flight(flightTwo);

        // A flight with departure and arrival in past
        List<Segment> flightThree = new ArrayList<>();
        flightThree.add(new Segment(threeDaysBeforeNowDateTime, threeDaysBeforeNowDateTime.plusHours(5)));
        flightThree.add(new Segment(threeDaysBeforeNowDateTime.plusHours(5).plusMinutes(30), threeDaysBeforeNowDateTime.plusHours(8)));
        Flight multiSegmentFlightInPast = new Flight(flightThree);

        flightsWithDepartureBeforeNow.add(flightWithDepartureBeforeNow);
        flightsWithDepartureBeforeNow.add(flightWithDepartureInPastAndNormalArrival);
        flightsWithDepartureBeforeNow.add(multiSegmentFlightInPast);
    }

    public void initFlightsWithArrivalDateTimeBeforeDepartureDateTime() {
        flightsWithArrivalDateTimeBeforeDepartureDateTime = new ArrayList<>();

        // A flight that arrives before it departs
        List<Segment> flightOne = new ArrayList<>();
        flightOne.add(new Segment(tomorrowDateTime, tomorrowDateTime.minusHours(5)));
        Flight flightOneWithArrivalBeforeDeparture = new Flight(flightOne);

        // A multi-segment flight that arrives before it departs
        List<Segment> flightTwo = new ArrayList<>();
        flightTwo.add(new Segment(tomorrowDateTime, tomorrowDateTime.minusHours(2)));
        flightTwo.add(new Segment(tomorrowDateTime.minusHours(3), tomorrowDateTime.minusHours(6)));
        Flight flightTwoWithArrivalBeforeDeparture = new Flight(flightTwo);

        flightsWithArrivalDateTimeBeforeDepartureDateTime.add(flightOneWithArrivalBeforeDeparture);
        flightsWithArrivalDateTimeBeforeDepartureDateTime.add(flightTwoWithArrivalBeforeDeparture);
    }

    public void initFlightsWithTotalTimeOnTheGroundExceedsTwoHours() {
        flightsWithTotalTimeOnTheGroundExceedsTwoHours = new ArrayList<>();

        // A flight with more than two hours ground time
        List<Segment> flightOne = new ArrayList<>();
        flightOne.add(new Segment(nowPlusTwoHoursDateTime, nowPlusTwoHoursDateTime.plusHours(5)));
        flightOne.add(new Segment(nowPlusTwoHoursDateTime.plusHours(7).plusMinutes(20), nowPlusTwoHoursDateTime.plusHours(11)));
        Flight flightOneWithExceededTime = new Flight(flightOne);

        // Another flight with more than two hours ground time
        List<Segment> flightTwo = new ArrayList<>();
        flightTwo.add(new Segment(tomorrowDateTime, tomorrowDateTime.plusHours(4)));
        flightTwo.add(new Segment(tomorrowDateTime.plusHours(5), tomorrowDateTime.plusHours(8)));
        flightTwo.add(new Segment(tomorrowDateTime.plusHours(9).plusMinutes(30), tomorrowDateTime.plusHours(14)));
        Flight flightTwoWithExceededTime = new Flight(flightTwo);

        flightsWithTotalTimeOnTheGroundExceedsTwoHours.add(flightOneWithExceededTime);
        flightsWithTotalTimeOnTheGroundExceedsTwoHours.add(flightTwoWithExceededTime);
    }

    @BeforeEach
    public void initTestFlights() {
        testFlights = new ArrayList<>();

        initNormalFlightsAfterCurrentDateTime();
        initFlightsWithDepartureBeforeNow();
        initFlightsWithArrivalDateTimeBeforeDepartureDateTime();
        initFlightsWithTotalTimeOnTheGroundExceedsTwoHours();

        testFlights.addAll(normalFlightsAfterCurrentDateTime);
        testFlights.addAll(flightsWithDepartureBeforeNow);
        testFlights.addAll(flightsWithArrivalDateTimeBeforeDepartureDateTime);
        testFlights.addAll(flightsWithTotalTimeOnTheGroundExceedsTwoHours);
    }

    @AfterEach
    void clearFlights() {
        testFlights.clear();
    }

    @Test
    void shouldReturnEmptyListAfterFilteringFlightsWithDepartureBeforeNow() {
        FlightFiltering flightFiltering = new FlightFilteringImpl(flightsWithDepartureBeforeNow);
        List<Flight> flightsAfterFiltering = flightFiltering
                .departureBeforeNow()
                .build();
        assertTrue(flightsAfterFiltering.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenFromTestFlightsRemovingBeforeNowAndFiltering() {
        List<Flight> expectedFlights = new ArrayList<>(testFlights);
        expectedFlights.removeAll(flightsWithDepartureBeforeNow);

        FlightFiltering flightFiltering = new FlightFilteringImpl(testFlights);
        List<Flight> flightsAfterFiltering = flightFiltering
                .departureBeforeNow()
                .build();
        assertEquals(expectedFlights, flightsAfterFiltering);
    }

    @Test
    void shouldReturnEmptyListAfterFilteringFlightsArrivalBeforeDeparture() {
        FlightFiltering flightFiltering = new FlightFilteringImpl(flightsWithArrivalDateTimeBeforeDepartureDateTime);
        List<Flight> flightsAfterFiltering = flightFiltering
                .segmentsWithArrivalDateBeforeDepartureDate()
                .build();
        assertTrue(flightsAfterFiltering.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenFromTestFlightsRemovingArrivalBeforeDepartureAndFiltering() {
        List<Flight> expectedFlights = new ArrayList<>(testFlights);
        expectedFlights.removeAll(flightsWithArrivalDateTimeBeforeDepartureDateTime);

        FlightFiltering flightFiltering = new FlightFilteringImpl(testFlights);
        List<Flight> flightsAfterFiltering = flightFiltering
                .segmentsWithArrivalDateBeforeDepartureDate()
                .build();
        assertEquals(expectedFlights, flightsAfterFiltering);
    }

    @Test
    void shouldReturnEmptyListAfterFilteringFlightsWithExceededTimeOnGround() {
        FlightFiltering flightFiltering = new FlightFilteringImpl(flightsWithTotalTimeOnTheGroundExceedsTwoHours);
        List<Flight> flightsAfterFiltering = flightFiltering
                .flightsWhereTotalTimeOnTheGroundExceedsTwoHours()
                .build();
        assertTrue(flightsAfterFiltering.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenFromTestFlightsRemovingFlightsWithExceededTimeOnGroundAndFiltering() {
        List<Flight> expectedFlights = new ArrayList<>(testFlights);
        expectedFlights.removeAll(flightsWithTotalTimeOnTheGroundExceedsTwoHours);

        FlightFiltering flightFiltering = new FlightFilteringImpl(testFlights);
        List<Flight> flightsAfterFiltering = flightFiltering
                .flightsWhereTotalTimeOnTheGroundExceedsTwoHours()
                .build();
        assertEquals(expectedFlights, flightsAfterFiltering);
    }

    @Test
    void shouldReturnTrueWhenComparingNormalFlightsWithTestFlightsAfterAllFilters() {
        List<Flight> expectedFlights = normalFlightsAfterCurrentDateTime;

        FlightFiltering flightFiltering = new FlightFilteringImpl(testFlights);
        List<Flight> flightsAfterFiltering = flightFiltering
                .departureBeforeNow()
                .segmentsWithArrivalDateBeforeDepartureDate()
                .flightsWhereTotalTimeOnTheGroundExceedsTwoHours()
                .build();
        assertEquals(expectedFlights, flightsAfterFiltering);
    }
}
